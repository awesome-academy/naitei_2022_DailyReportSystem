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

import trainingManagementSystem.model.Comment;
import trainingManagementSystem.model.Report;
import trainingManagementSystem.model.Search;
import trainingManagementSystem.model.User;
import trainingManagementSystem.service.CommentServices;
import trainingManagementSystem.service.DivisionServices;
import trainingManagementSystem.service.UserServices;

@Controller
public class UserController {

	@Autowired
	UserServices userService;

	@Autowired
	DivisionServices divisionServices;

	@Autowired
	CommentServices commentServices;

	@RequestMapping(value = { "/users/create" }, method = RequestMethod.POST)
	public String saveUser(ModelMap model, @ModelAttribute("user") User user) {

		userService.saveUser(user);
		model.addAttribute("message", "Success adding user!");
		return "users/index";
	}

	@RequestMapping(value = { "/users/reports/create" }, method = RequestMethod.GET)
	public String createReportPage(ModelMap model) {
		model.addAttribute("report", new Report());
		return "users/createReport";
	}

	@RequestMapping(value = { "/users/reports" }, method = RequestMethod.POST)
	public String createReport(ModelMap model, @ModelAttribute("report") Report report) {
		// TODO: author -> get Id
		report.setUser(userService.getById(2, false)); // 2: userId
		report.setReviewer(userService.getById(5, false)); // 5: reviewerId
		userService.saveReport(report);

		int pageNumber = 1;
		int pageSize = 6;
		int userId = 2;

		int reportsLength = userService.getReportsLength(userId, report.getDate().toLocalDate());
		List<Report> reports = userService.paginationReports(userId, pageNumber - 1, pageSize,
				report.getDate().toLocalDate());

		model.addAttribute("reports", reports);
		model.addAttribute("totalPages", (int) Math.ceil(reportsLength / ((double) pageSize)));
		model.addAttribute("pageNumber", pageNumber);
		model.addAttribute("searchParams", new Search("", report.getDate().toLocalDate().toString()));

		return "users/reports";
	}

	@RequestMapping(value = { "/users/reports" }, method = RequestMethod.GET)
	public String getAllReports(ModelMap model, @ModelAttribute("searchParams") Search searchParams,
			@RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
			@RequestParam(value = "reportDate", required = false, defaultValue = "") String reportDate) {
		int pageSize = 6;
		int userId = 2;

		if (reportDate.length() == 0 && searchParams.getDate() != null) {
			reportDate = searchParams.getDate();
		} else if (reportDate.length() != 0 && searchParams.getDate() == null) {
			searchParams.setDate(reportDate);
		}
		LocalDate reportDateSearch = null;
		if (reportDate.length() != 0) {
			reportDateSearch = LocalDate.parse(reportDate);
		}
		int reportsLength = userService.getReportsLength(userId, reportDateSearch);
		List<Report> reports = userService.paginationReports(userId, pageNumber - 1, pageSize, reportDateSearch);

		model.addAttribute("reports", reports);
		model.addAttribute("totalPages", (int) Math.ceil(reportsLength / ((double) pageSize)));
		model.addAttribute("pageNumber", pageNumber);

		return "users/reports";
	}

	@RequestMapping(value = { "users/reports/{id}" }, method = RequestMethod.GET)
	public String getById(ModelMap model, @PathVariable Integer id) {
		// not lock
		model.addAttribute("report", divisionServices.getReportById(id, false));
		model.addAttribute("comment", new Comment());
		model.addAttribute("deletedComment", new Comment());
		model.addAttribute("idReport", id);

		return "users/comments";
	}

	@RequestMapping(value = { "users/reports/{id}/comments" }, method = RequestMethod.POST)
	public String saveComment(ModelMap model, @ModelAttribute("comment") Comment comment, @PathVariable Integer id) {
		comment.setReport(divisionServices.getReportById(id, false));
		comment.setUser(userService.getById(2, false));
		if (comment.getContent() != null) {
			commentServices.saveComment(comment);
		}

		model.addAttribute("report", divisionServices.getReportById(id, false));
		model.addAttribute("comment", new Comment());
		model.addAttribute("deletedComment", new Comment());
		model.addAttribute("idReport", id);

		return "users/comments";
	}

	@RequestMapping(value = "users/comments/{id}", method = RequestMethod.DELETE)
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
