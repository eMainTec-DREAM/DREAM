package common.report;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.poi.hssf.converter.ExcelToFoUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.util.XMLHelper;
import org.dom4j.DocumentException;
import org.w3c.dom.Document;

public class Excel2XslFo
{
    public static void convertExcel2PDF(String excelFilePath, String poFilePath) throws IOException, DocumentException
    {
//        try
//        {
//            Document doc = Excel2XslFo.process(new File(excelFilePath));
//        
//            FileOutputStream out = new FileOutputStream(new File(poFilePath));
//            DOMSource domSource = new DOMSource(doc);
//            StreamResult streamResult = new StreamResult(new OutputStreamWriter(out, "UTF-8"));
//        
//            TransformerFactory tf = TransformerFactory.newInstance();
//            Transformer serializer = tf.newTransformer();
//            
//            // set encoding from a command argument
//            serializer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
//            serializer.setOutputProperty(OutputKeys.INDENT, "no");
//            serializer.setOutputProperty(OutputKeys.METHOD, "xml");
//            
//            serializer.transform( domSource, streamResult );
//            
//            out.close();
//        }
//        catch ( Exception e )
//        {
//            e.printStackTrace();
//        }
    }
    
    
    /**
     * Converts Excel file (97-2007) into XSL FO file.
     * 
     * @param xlsFile
     *            file to process
     * @return DOM representation of result XSL FO
     */
//    private static Document process( File xlsFile ) throws Exception
//    {
//        final HSSFWorkbook workbook = ExcelToFoUtils.loadXls( xlsFile );
//        ExcelToFoConverter excelToHtmlConverter = new ExcelToFoConverter(
//                XMLHelper.getDocumentBuilderFactory().newDocumentBuilder()
//                        .newDocument() );
//        
//        excelToHtmlConverter.setOutputRowNumbers(false);
//        excelToHtmlConverter.setOutputColumnHeaders(false);
//        
//        excelToHtmlConverter.processWorkbook( workbook );
//        
//        return excelToHtmlConverter.getDocument();
//    }
}