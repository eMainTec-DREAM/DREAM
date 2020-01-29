package common.report;

//Java
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
//JAXP
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import common.util.DateUtil;

/**
 * Jump PDF Report Module
 * @author  javaworker
 * @version $Id: ReportPDF.java,v 1.2 2015/12/21 05:34:57 hankyul Exp $
 */
public class ReportPDF
{
    /** Report Map For Set Value */
    private Map<String, Object> reportMap = null;
    
    /** Report Map For Set Value List */
    private List<Map<String, Object>> reportMapList = null;
    /** Web Root in System */
    private String basePath = null;
    /** XSL-FO File Name */
    private String foFileName = null;
    /** PDF File Name */
    private String pdfFileName = null;
    
    public ReportPDF(Object _reportObj, String basePath, String foFileName, String pdfFileName)
    {
        if (_reportObj instanceof Map)
        {
            this.reportMapList = new ArrayList<Map<String, Object>>();
            this.reportMapList.add((Map)_reportObj);
        }
        else
        {
            this.reportMapList = (List)_reportObj;
        }
        
        this.basePath = basePath;
        this.foFileName = foFileName;
        this.pdfFileName = pdfFileName;
    }

    /**
     * Generate PDF File From XSL-FO File
     * @throws Exception
     */
    public void generatePDF() throws Exception
    {
        FopFactory fopFactory = FopFactory.newInstance();
        FileOutputStream fops = new FileOutputStream(new File(this.basePath + "dream\\report\\temp\\" + this.pdfFileName + ".pdf"));
        OutputStream out = new BufferedOutputStream(fops);
        
        // fop library property
        fopFactory.setUserConfig(new File(this.basePath + "dream\\report\\font\\fop.xconf"));
        try 
        {
            // Construct fop with desired output format
            Fop fop = fopFactory.newFop("application/pdf", out);
            
            // Setup JAXP using identity transformer
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(); // identity transformer
            
            //==========================================================
            // Generate FO File from Template FO File.
            File foFile = this.setReportValue();
            
            // Step 5: Setup input and output for XSLT transformation
            // Setup input stream
            Source src = new StreamSource(foFile);
            
            // Resulting SAX events (the generated FO) must be piped through to FOP
            Result res = new SAXResult(fop.getDefaultHandler());
            
            transformer.setOutputProperty( OutputKeys.ENCODING, "UTF-8" );
            
            // Start XSLT transformation and FOP processing
            transformer.transform(src, res);
        }
        finally 
        {
            out.close();
            fops.close();
        }
    }

    /**
     * Set Report Value in Template FO & Create New XSL-FO File
     * @return
     * @throws Exception
     */
    private File setReportValue() throws Exception
    {
        DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document doc = dBuilder.parse(new File(this.basePath + "dream\\report\\" + this.foFileName + ".fo"));
        
        // Get the document's root XML node, this node must be one in XSL.
        NodeList tableNodes = doc.getElementsByTagName("fo:page-sequence");
        Node pageSeqNode = tableNodes.item(0);
        Node tempSeqNode = pageSeqNode.cloneNode(true); // value 가 셋팅되지 않는 page를 복사해 놓는다.;
        
        for (int i=0; i<reportMapList.size(); i++)
        {
            if (i == 0)
            {
                Element e = (Element)pageSeqNode;
                e.setAttribute("force-page-count", "no-force");
            }
            else
            {
                pageSeqNode = tempSeqNode.cloneNode(true);
                
                Element e = (Element)pageSeqNode;
//                e.setAttribute("initial-page-number", "1");
                e.removeAttribute("force-page-count");
                
                tableNodes.item(0).getParentNode().appendChild(pageSeqNode);
            }
            
            this.reportMap = reportMapList.get(i);
            this.reportMap.put("PAGE_ID", "PAGE_"+i);
            this.reportMap.put("TOTAL_PAGE", reportMapList.size());

            // set Key value
            this.setMapValue(pageSeqNode, this.reportMap);
            
            // set list map value
            this.setListValue(pageSeqNode);
        }
        
        // File 객체 추출
        Transformer trans = TransformerFactory.newInstance().newTransformer();

        //정의 변형할 원본 개체
        DOMSource xml = new DOMSource(doc);
        //정의 다음으로 변환 대상 파일이 한다
        File foFile = new File(this.basePath + "dream\\report\\temp\\" + this.pdfFileName + ".fo");
        StreamResult s = new StreamResult(foFile);
        //변환 시작
        trans.transform(xml, s);
        
        return foFile;
    }
    
    /**
     * set map value in Node
     * @param _node
     * @param _map
     */
    private void setMapValue(Node _node, Map _map)
    {
        if (_map == null) return;
        
        NodeList childNodes = _node.getChildNodes();
        for (int x = 0; x < childNodes.getLength(); x++ )
        {
            Node data = childNodes.item(x);
            if ( data.getNodeType() == Node.TEXT_NODE )
            {
                String nodeValue = this.replaceMapValue(data.getNodeValue(), _map);          
                data.setNodeValue(nodeValue);
            }
            else
            {
                this.setMapValue(data, _map);
            }
        }
    }

    /**
     * replace key to value in nodeValue;
     * @param nodeValue
     * @param _map
     * @return
     */
    private String replaceMapValue(String nodeValue, Map<String, Object> _map)
    {
        for (Entry<String, Object> keyEntry : _map.entrySet()) 
        {
            String key = keyEntry.getKey();
            key=key.toUpperCase();
            Object valueObj = keyEntry.getValue();
            if (nodeValue.indexOf("#"+key+"#")>-1)
            {
                if (valueObj != null) 
                {
                    nodeValue = nodeValue.replaceAll("#"+key+"#", valueObj.toString());
                }
                else
                {
                    nodeValue = nodeValue.replaceAll("#"+key+"#", "");
                }
            }
        }
        
        return nodeValue;
    }
    
    /**
     * set list map
     * @param _node
     */
    private void setListValue(Node _node)
    {
        if (this.reportMap == null) return;
        
        for (Entry<String, Object> keyEntry : this.reportMap.entrySet()) 
        {
            String key = keyEntry.getKey();
            Object valueObj = keyEntry.getValue();
            
            if (valueObj instanceof List) 
            {
                this.setListValue(_node, key);
            }
        }
    }
    
    /**
     * set list map
     * @param _node
     */
    private void setListValue(Node _node, Map<String, Object> _map)
    {
        if (_map == null) return;
        
        for (Entry<String, Object> keyEntry : _map.entrySet()) 
        {
            String key = keyEntry.getKey();
            Object valueObj = keyEntry.getValue();
            
            if (valueObj instanceof List) 
            {
                this.setListValue(_node, key, _map);
            }
        }
    }
    
    /**
     * set list value in node
     * List inside List
     * @param _node
     * @param listKey
     */
    private void setListValue(Node _node, String listKey, Map _map)
    {
        List<Map> resultList = (List)_map.get(listKey);
        
        Node listNode = this.getNodeById(_node, "#" + listKey + "#");
        if (listNode == null) return;
        
        Node pNode = listNode.getParentNode();
        
        for (Map _listMap:resultList)
        {
            Node cloneNode = listNode.cloneNode(true);
            Element e = (Element)cloneNode;
            e.removeAttribute("id");    // E.id must be unique.(remove or rename)
            
            this.setMapValue(cloneNode, _listMap);
            
            pNode.insertBefore(cloneNode, listNode);
        }
        
        // delete template node
        pNode.removeChild(listNode);
    }
    
    /**
     * set list value in node
     * @param _node
     * @param listKey
     */
    private void setListValue(Node _node, String listKey)
    {
        List<Map> resultList = (List)reportMap.get(listKey);
        
        Node listNode = this.getNodeById(_node, "#" + listKey + "#");
        if (listNode == null) return;
        
        Node pNode = listNode.getParentNode();
        
        for (Map _map:resultList)
        {
            Node cloneNode = listNode.cloneNode(true);
            Element e = (Element)cloneNode;
            e.removeAttribute("id");    // E.id must be unique.(remove or rename)
            
            this.setMapValue(cloneNode, _map);
            
            this.setListValue(cloneNode, _map);
            
            pNode.insertBefore(cloneNode, listNode);
        }
        
        // delete template node
        pNode.removeChild(listNode);
    }
    
    /**
     * get element node by id
     * @param _node
     * @param _attrId
     * @return
     */
    private Node getNodeById(Node _node, String _attrId)
    {
        NodeList childNodes = _node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++ )
        {
            Node cNode = childNodes.item(i);
            if (checkAttrId(cNode, _attrId))  return cNode;
            
            Node _resultNode = this.getNodeById(cNode, _attrId);
            if (_resultNode != null) return _resultNode;
        }
        
        return null;
    }
    
    /**
     * check attribute id
     * @param _node
     * @param _attrId
     * @return
     */
    private boolean checkAttrId(Node _node, String _attrId)
    {
        if (_node.getNodeType() == Node.ELEMENT_NODE)
        {
            Element e = (Element)_node;
            String id = e.getAttribute("id");
            
            if (_attrId.equals(id)) return true;
        }
        return false;
    }
    
    /**
     * temp 폴더의 쌓인 결과 값들을 삭제한다.
     * @author  javaworker
     * @version $Id: ReportPDF.java,v 1.2 2015/12/21 05:34:57 hankyul Exp $
     * @since   1.0
     *
     */
    public void deleteTempPdfFiles()
    {
        //=======================================================
        // 임시 파일 dir 에 모든 파일 을 삭제한다.
        File dirFile = new File(basePath + "dream\\report\\temp\\");
        File [] files = dirFile.listFiles();
        if (files == null) return;
        
        for (int i=0; i<files.length; i++)
        {
            if ("notice.txt".equals(files[i].getName())) continue;
            
            // 일자가 오늘날짜 이전에 생성된것들은 모두 삭제한다.
            long fileDateLong = files[i].lastModified();
            Date fileDate = new Date(fileDateLong);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            
            // 파일 수정일자
            String madeDate = dateFormat.format(fileDate);
            
            // 현재일자
            String currentDate = DateUtil.getDateTime("yyyyMMdd");
            
            try
            {
                // 파일생성일자 < 현재일자 라면 삭제한다.
                if (DateUtil.compareDate(madeDate, null, "<", currentDate, null))
                {
                    files[i].delete();
                }
            }
            catch(Exception ex)
            {
                ex.printStackTrace();                
            }
        }
    }
}