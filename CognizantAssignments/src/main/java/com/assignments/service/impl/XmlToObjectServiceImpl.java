package com.assignments.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.UnmarshalException;
import javax.xml.bind.Unmarshaller;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.assignments.domain.CustomerRecord;
import com.assignments.service.XmlToObjectService;

/**
 * @author Jagadish Anala
 *
 */
@Service("XmlToObjectService")
public class XmlToObjectServiceImpl implements XmlToObjectService {

	@Override
	public CustomerRecord processXmlFile(MultipartFile multipartFile)
			throws UnmarshalException, JAXBException, IOException {
		CustomerRecord customerRecord = null;
		File file = convertToFile(multipartFile);
		JAXBContext jaxbContext = JAXBContext.newInstance(CustomerRecord.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		try {
			customerRecord = (CustomerRecord) jaxbUnmarshaller.unmarshal(file);
			return customerRecord;
		} finally {
			deleteFile(file);
		}
	}

	private File convertToFile(MultipartFile multipartFile) throws IOException {
		File convertedFile = new File(multipartFile.getOriginalFilename());
		convertedFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(convertedFile);
		fos.write(multipartFile.getBytes());
		fos.close();
		return convertedFile;
	}

	private void deleteFile(File file) {
		if (file.exists())
			file.delete();
	}
}
