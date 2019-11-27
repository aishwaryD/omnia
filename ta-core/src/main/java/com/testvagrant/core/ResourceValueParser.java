package com.testvagrant.core;

import com.testvagrant.model.*;
import com.testvagrant.util.*;
import org.testng.Assert;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.*;
import java.util.*;

/**
 * Created by Aishwarya Dwivedi
 */
public class ResourceValueParser
{

    public static List<Map<String, String>> getAllValues(String fileName, int dataCount)
    {
        Map<String,String> valuesMap = new HashMap<String,String>();
        List<Map<String, String>> valueList = new ArrayList<Map<String, String>>();
        String key;
        try {
            for(int count = 0; count < dataCount ; count++) {
                valuesMap = new HashMap<String, String>();
                String[] fileNames = fileName.split("\\^");
                for(String file: fileNames) {
                    Element documentElement = getDocumentElement(file);
                    NodeList nodeList =  documentElement.getElementsByTagName(ResourceValueParserConstants.value);
                    if (nodeList != null && nodeList.getLength() > 0) {
                        for (int i = 0; i < nodeList.getLength(); i++) {
                            Node node = nodeList.item(i);
                            ValueParams val = getValueParams(node);
                            if(val.ValueAttributes.containsKey("type") && val.ValueAttributes.get("type").equalsIgnoreCase("fromvaluegroup")) {
                                if(val.ValueAttributes.containsKey("defaultvalue")) {
                                    if(val.ValueAttributes.get("defaultvalue").equalsIgnoreCase("none"))
                                        continue;
                                    else if(val.ValueAttributes.get("defaultvalue").equalsIgnoreCase("random"))
                                        parseAllValuesFromGroup(valuesMap, (Element) node);
                                    else
                                        setValueToParticularGroupInternal(valuesMap, documentElement, val.ValueAttributes.get("id"), val.ValueAttributes.get("defaultvalue"));
                                }
                                else {
                                    parseAllValuesFromGroup(valuesMap, (Element) node);
                                }
                            }
                            else {
                                key = getNodeAttribute(node, ResourceValueParserConstants.id);
                                val.finalValue = getValueFromValueParam(val);
                                valuesMap.put(key, val.finalValue);
                            }
                        }
                    }
                }
                fillContextValuesIntoValues(valuesMap);
                valueList.add(valuesMap);
            }
        }
        catch (Exception e)
        {
            LogManager.logFatal("Error in ResourceValueParser.getAllValues", e);
        }
        return valueList;
    }

    private static void fillContextValuesIntoValues(Map<String, String> values)
    {
        values.put("xEnvironmentName", BaseContext.getEnvironmentName());
        values.put("EnvironmentURL", BaseContext.getEnvironmentURL());
    }

    public static Map<String,String> getAllValues(String fileName)
    {
        return getAllValues(fileName, 1).get(0);
    }

    private static Element getDocumentElement(String fileName) throws ParserConfigurationException, SAXException, IOException
    {
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(FileManager.getAbsoluteFilePath(BaseContext.getLocale().name()) + "/" + fileName);
        return document.getDocumentElement();
    }

    private static void parseAllValuesFromGroup(Map<String, String> valuesMap, Element node) {
        Element element = node;
        NodeList groupNodeList = element.getElementsByTagName(ResourceValueParserConstants.group);
        int countOfGroups = groupNodeList.getLength();
        Random RandomRange = new Random();
        int randomNumber = RandomRange.nextInt(countOfGroups);
        Element groupElement = (Element)groupNodeList.item(randomNumber);
        NodeList groupValueNodeList = groupElement.getElementsByTagName(ResourceValueParserConstants.groupValue);
        for (int j = 0; j < groupValueNodeList.getLength(); j++) {
            ValueParams groupVal = getValueParams(groupValueNodeList.item(j));
            String groupKey = getNodeAttribute(groupValueNodeList.item(j), ResourceValueParserConstants.id);
            groupVal.finalValue = getValueFromValueParam(groupVal);
            valuesMap.put(groupKey, groupVal.finalValue);
        }
    }

    private static boolean setValueToParticularGroupInternal(Map<String, String> valuesMap, Element element, String valueId, String groupId) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
        String groupPath = String.format("/values/value[@id='%s']/group[@id='%s']/groupvalue", valueId, groupId);
       LogManager.logInfo(groupPath);
        XPath xPath = XPathFactory.newInstance().newXPath();
        NodeList nodeList = (NodeList)xPath.evaluate(groupPath, element, XPathConstants.NODESET);
        if (nodeList != null && nodeList.getLength() > 0) {
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                ValueParams val = getValueParams(node);
                String key = getNodeAttribute(nodeList.item(i), ResourceValueParserConstants.id);
                val.finalValue = getValueFromValueParam(val);
                valuesMap.put(key, val.finalValue);
            }
            return true;
        }
        return false;
    }

    public static void setValueToParticularGroup(String filePath, Map<String, String> valuesMap, String valueId, String groupId)
    {
        try {
            groupId = groupId.replaceAll("\\s+","").replace("-","").toLowerCase(); //remove space from group id
            String[] fileNames = filePath.split("\\^");
            for(String file: fileNames) {
                Element documentElement = getDocumentElement(file);
                boolean found = setValueToParticularGroupInternal(valuesMap, documentElement, valueId, groupId);
                if(found)
                    break;
            }

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    private static String getValue(Map<String, String> valueAttributes)
    {
        String defaultValue = null, finalValue = null;
        try {
            if (valueAttributes.containsKey(XMLAttributesConstants.defaultValue)) {
                defaultValue = valueAttributes.get(XMLAttributesConstants.defaultValue);
                if(defaultValue.equalsIgnoreCase("null") && valueAttributes.get("asstring").equalsIgnoreCase("false"))
                    defaultValue = null;
            }
            finalValue = defaultValue;

            if (!StringUtil.isNullOrEmpty(defaultValue) && defaultValue.compareToIgnoreCase(XMLAttributesConstants.random) == 0) {
                finalValue = RandomDataGenerator.CreateRandomData(valueAttributes);
            }
        }
        catch(Exception e)
        {
            LogManager.logFatal("Error in Value.GetValue. ValueAttributes:" + valueAttributes.toString());
            LogManager.logFatal("Exception" + e.getMessage());
            Assert.fail(e.getMessage());
        }
        return finalValue;
    }

    private static String getValueFromValueParam(ValueParams val)
    {
        return getValue(val.ValueAttributes);
    }

    private static ValueParams getValueParams(Node node)
    {
        ValueParams val = new ValueParams();
        val.ValueAttributes = new HashMap<String, String>();
        NamedNodeMap namedNodeMap = node.getAttributes();
        for(int i = 0; i < namedNodeMap.getLength(); i++)
        {
            Node attr = namedNodeMap.item(i);
            val.ValueAttributes.put(attr.getNodeName(), attr.getNodeValue());
        }
        return val;
    }

    public static String getNodeAttribute(Node node, String attributeName)
    {
        return node.getAttributes().getNamedItem(attributeName).getNodeValue();
    }

}
