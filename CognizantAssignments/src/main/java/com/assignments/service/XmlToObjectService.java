package com.assignments.service;

import org.springframework.web.multipart.MultipartFile;

import com.assignments.domain.CustomerRecord;

/**
 * @author Jagadish Anala
 *
 */
public interface XmlToObjectService {
	public CustomerRecord processXmlFile(MultipartFile multipartFile) throws Exception;
}
