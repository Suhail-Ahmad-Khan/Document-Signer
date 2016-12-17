package org.bridgelabz.docsigner.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.bridgelabz.docsigner.model.Document;
import org.bridgelabz.docsigner.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DocumentController {

	@Autowired
	private DocumentService documentService;

	@RequestMapping(value = "/addDocuments", method = RequestMethod.GET)
	public String getData(Model model) {
		Document document = new Document();

		model.addAttribute("document", document);
		return "addDocuments";
	}

	@RequestMapping(value = "/addDocuments", method = RequestMethod.POST)
	public String addDocument(@ModelAttribute("document") Document document, BindingResult result, MultipartFile file) {
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getName());
		InputStream io = null;
		try {
			document.setFilename(file.getOriginalFilename());
			document.setContentType(file.getContentType());
			document.setName(file.getName());
			io = file.getInputStream();

			documentService.addDocument(document, io);
			io.close();
			/*
			 * FileOutputStream fos = new
			 * FileOutputStream("/home/bridgeit/"+file.getOriginalFilename());
			 * byte [] buff = new byte[8000]; while( io.available() > 0 ) { int
			 * n = io.read(buff); fos.write(buff, 0, n); } fos.close();
			 * io.close();
			 */

		} catch (IOException e) {
			e.printStackTrace();
		}
		return "success";
	}

	@RequestMapping(value = "/documentList", method = RequestMethod.GET)
	public ModelAndView listAllDocuments(@RequestParam("userId") Integer userId, Model model) {
		List<Document> documentInfo = documentService.listDocuments(userId);

		return new ModelAndView("documentList", "documentInfo", documentInfo);

	}

	@RequestMapping(value = "/documentDetails", method = RequestMethod.GET)
	public ModelAndView displayDocumentDetails(@RequestParam("id") Integer id, Model model) {
		List<Document> documentDetails = documentService.listDocumentDetails(id);
		/* model.addAttribute("msg", id); */
		return new ModelAndView("documentDetails", "documentDetails", documentDetails);

	}
}