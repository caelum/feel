package br.com.caelum.feel.feedback.reports.application.csv;

import java.io.StringWriter;
import java.util.List;

import org.springframework.stereotype.Component;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

@Component
public class CsvWriter {

	private static CsvPreference preference = new CsvPreference.Builder('"', ';', "\n").build();

	public String write(List<?> lines, String[] header, CellProcessor[] processors) {
		StringWriter writer = new StringWriter();

		try (ICsvBeanWriter beanWriter = new CsvBeanWriter(writer, preference)) {

			beanWriter.writeHeader(header);
			for (final Object line : lines) {
				beanWriter.write(line, header, processors);
			}

		} catch (Exception exception) {
			throw new RuntimeException(exception);
		}

		return writer.toString();
	}
}
