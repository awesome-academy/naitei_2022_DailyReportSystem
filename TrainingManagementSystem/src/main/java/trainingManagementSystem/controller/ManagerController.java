package trainingManagementSystem.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import trainingManagementSystem.model.Comment;
import trainingManagementSystem.model.Division;
import trainingManagementSystem.model.Search;
import trainingManagementSystem.model.User;
import trainingManagementSystem.service.CommentServices;
import trainingManagementSystem.service.DivisionServices;
import trainingManagementSystem.service.ManagerServices;
import trainingManagementSystem.service.UserServices;

@Controller
@RequestMapping("/manager")
public class ManagerController {

	@Autowired
	UserServices userServices;
	@Autowired
	DivisionServices divisionServices;
	@Autowired
	ManagerServices managerServices;
	@Autowired
	CommentServices commentServices;

	@RequestMapping(value = "divisions/{id}", method = RequestMethod.GET)
	public ModelAndView index(@PathVariable("id") int id) {
		
		ModelAndView mv = new ModelAndView("managers/manage_division");
		mv.addObject("users", userServices.getUserByDivisionId(id));	
//		mv.addObject("users", userServices.getAllUser());
		mv.addObject("division", divisionServices.getById(id));
		return mv;
	}

	@RequestMapping(value = { "/reports" }, method = RequestMethod.GET)
	public String getAllReports(ModelMap model, @ModelAttribute("searchParams") Search searchParams,
			@RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
			@RequestParam(value = "textSearch", required = false, defaultValue = "") String nameSearch,
			@RequestParam(value = "reportDate", required = false, defaultValue = "") String reportDate) {
		int pageSize = 6;
		int divisionId = 1;
		if (nameSearch == "" && searchParams.getTextSearch() != "") {
			nameSearch = searchParams.getTextSearch();
		} else if (nameSearch != "" && searchParams.getTextSearch() == "") {
			searchParams.setTextSearch(nameSearch);
		}

		if (reportDate.length() == 0 && searchParams.getDate() != null) {
			reportDate = searchParams.getDate();
		} else if (reportDate.length() != 0 && searchParams.getDate() == null) {
			searchParams.setDate(reportDate);
		}
		LocalDate reportDateSearch = null;
		if (reportDate.length() != 0) {
			reportDateSearch = LocalDate.parse(reportDate);
		}
		int reportsLength = divisionServices.getReportsLength(divisionId, nameSearch, reportDateSearch);

		model.addAttribute("reports",
				divisionServices.paginationReports(divisionId, nameSearch, pageNumber - 1, pageSize, reportDateSearch));

		model.addAttribute("totalPages", (int) Math.ceil(reportsLength / ((double) pageSize)));
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("searchParams", new Search(nameSearch, reportDate));

		return "managers/reports";
	}

	@RequestMapping(value = { "/reports/{id}" }, method = RequestMethod.GET)
	public String getById(ModelMap model, @PathVariable Integer id) {
		// not lock
		model.addAttribute("report", divisionServices.getReportById(id, false));
		model.addAttribute("comment", new Comment());
		model.addAttribute("deletedComment", new Comment());
		model.addAttribute("idReport", id);

		return "managers/comments";
	}

	@RequestMapping(value = { "/reports/{id}/comments" }, method = RequestMethod.POST)
	public String saveComment(ModelMap model, @ModelAttribute("comment") Comment comment, @PathVariable Integer id) {
		comment.setReport(divisionServices.getReportById(id, false));
		comment.setUser(userServices.getById(2, false));
		if (comment.getContent() != null) {
			commentServices.saveComment(comment);
		}

		model.addAttribute("report", divisionServices.getReportById(id, false));
		model.addAttribute("comment", new Comment());
		model.addAttribute("deletedComment", new Comment());
		model.addAttribute("idReport", id);

		return "managers/comments";
	}
	
	@RequestMapping(value="/{id}/remove", method=RequestMethod.POST)
	@ResponseBody
	public String removeUserFromDivision(@PathVariable("id") int id) {
			userServices.removeUserFromDivision(id);
			return "success";
	}
	
	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public ModelAndView addUser() {
		ModelAndView mv = new ModelAndView("managers/add_user");
		mv.addObject("users", userServices.getNewUsers());
		return mv;
	}
	
	@RequestMapping(value="/add-user/{divisionId}/{userId}", method=RequestMethod.POST)
	@ResponseBody
	public String addUserToDivision(@PathVariable("userId") int userId, @PathVariable("divisionId") int divisionId) {
		try {
			userServices.addUserToDivision(divisionId, userId);
			return "success";
		} catch(Exception e) {
			return "error";
		}
	}

	@RequestMapping(value = "/comments/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteComment(@PathVariable("id") int deletedCommentId) {
		try {
			commentServices.deleteComment(deletedCommentId);
			return "success";
		} catch (Exception e) {
			throw e;
		}
	}
}
