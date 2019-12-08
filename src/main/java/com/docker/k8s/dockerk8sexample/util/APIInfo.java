package com.docker.k8s.dockerk8sexample.util;

import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

import org.apache.commons.io.IOUtils;

import org.springframework.core.io.ClassPathResource;

public class APIInfo {

	/**
	 * @return String - API Information.
	 */
	@SuppressWarnings("all")
	public String fetchAPIInfo() {

		InputStream istr = null;
		final StringBuilder vars = new StringBuilder(5000);
		try {
			// Service Version.
			final ClassPathResource resource = new ClassPathResource("version.txt");
			istr = resource.getInputStream();
			final String versionStr = IOUtils.toString(istr, "UTF-8");
			vars.append("Microservice ProjectVersion-BuildNumber=").append(versionStr);

			Enumeration<URL> resources = getClass().getClassLoader().getResources("META-INF/MANIFEST.MF");

			Manifest manifest = new Manifest(resources.nextElement().openStream());
			Attributes attrs = manifest.getMainAttributes();

			vars.append(System.lineSeparator()).append("Cloud Manifest Properties").append(System.lineSeparator())
					.append("========================").append(System.lineSeparator());
			final int attrSize = attrs.size();

			final Iterator attrAttrItr = attrs.entrySet().iterator();
			Map.Entry attrEntry = null;
			while (attrAttrItr.hasNext()) {
				attrEntry = (Map.Entry) attrAttrItr.next();
				vars.append(attrEntry.getKey()).append("=").append(attrEntry.getValue()).append(System.lineSeparator());
			}

			// All remaining attributes.
			Map<String, Attributes> manAttrs = manifest.getEntries();
			Iterator manItr = manAttrs.entrySet().iterator();
			Map.Entry mapEntry = null;
			while (manItr.hasNext()) {
				mapEntry = (Map.Entry) manItr.next();
				vars.append(mapEntry.getKey()).append("=").append(mapEntry.getValue()).append(System.lineSeparator());
			}

			// Environment variables
			vars.append(System.lineSeparator()).append("Environment Variables").append(System.lineSeparator())
					.append("========================").append(System.lineSeparator());
			Map<String, String> envMap = System.getenv();
			Iterator<Map.Entry<String, String>> envItr = envMap.entrySet().iterator();
			Map.Entry pair = null;

			String creds = null;
			String uidpwd = null;
			String kie = null, val = null;
			while (envItr.hasNext()) {
				pair = (Map.Entry) envItr.next();
				kie = (String) pair.getKey();
				val = (String) pair.getValue();

				vars.append(kie).append("=").append(val).append(System.lineSeparator());
			}

			// System Properties.
			vars.append(System.lineSeparator()).append("System Variables").append(System.lineSeparator())
					.append("========================").append(System.lineSeparator());
			Properties pros = System.getProperties();
			Iterator prosItr = pros.entrySet().iterator();
			Map.Entry sysPair = null;
			while (prosItr.hasNext()) {
				sysPair = (Map.Entry) prosItr.next();
				vars.append(sysPair.getKey()).append("=").append(sysPair.getValue()).append(System.lineSeparator());
			}

		} catch (Exception e) {

			vars.append(System.lineSeparator()).append("IOException caught while fetching APIInfo");
			System.out.println(vars.toString());
		} finally {
			IOUtils.closeQuietly(istr);
		}

		return vars.toString();
	}
}
