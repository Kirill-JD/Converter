package com.example.service;

import com.example.model.Currency;
import com.example.repos.CurrencyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class SAXService {

    private final List<Currency> cur = new ArrayList();

    public List<Currency> SAXCheck() throws SAXException, IOException {
        String pre_apiURL = "http://www.cbr.ru/scripts/XML_daily.asp";
        URL url = new URL(pre_apiURL);

        AdvancedXMLHandler handler = new AdvancedXMLHandler();

        XMLReader myReader = XMLReaderFactory.createXMLReader();
        myReader.setContentHandler(handler);
        myReader.parse(new InputSource(url.openStream()));

        handler.rusValute();
        return cur;
    }

    private class AdvancedXMLHandler extends DefaultHandler {
        private String valuteId;
        private String charCode;
        private String name;
        private String value;
        private String date;
        private String lastElementName;

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) {
            lastElementName = qName;
            if (qName.equals("ValCurs")) {
                date = attributes.getValue("Date");
            }
            if (qName.equals("Valute")) {
                valuteId = attributes.getValue("ID");
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) {
            String information = new String(ch, start, length);

            information = information.replace("\n", "").trim();

            if (!information.isEmpty()) {
                if (lastElementName.equals("Name"))
                    name = information;
                if (lastElementName.equals("Value"))
                    value = information;
                if (lastElementName.equals("CharCode"))
                    charCode = information;
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) {
            if ((name != null && !name.isEmpty()) &&
                    (value != null && !value.isEmpty()) &&
                    (charCode != null && !charCode.isEmpty())) {

                cur.add(new Currency(valuteId, charCode, name, value, date));
                name = null;
                value = null;
                charCode = null;
                valuteId = null;
            }
        }

        public void rusValute() {
            cur.add(new Currency("", "RUB", "Российский рубль", "1", date));
        }
    }

}
