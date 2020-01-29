package dream.consult.program.foreport.service.spring;

import common.report.Excel2XslFo;
import common.util.FileUtil;
import dream.consult.program.foreport.dto.MaReportCommonDTO;
import dream.consult.program.foreport.service.MaReportService;

/**
 * 레포트변환 serviceimpl
 * @author kim21017
 * @version $Id: MaReportServiceImpl.java,v 1.0 2015/12/02 09:12:51 kim21017 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maReportServiceTarget"
 * @spring.txbn id="maReportService"
 */
public class MaReportServiceImpl implements MaReportService
{
    @Override
    public void createXslFo(MaReportCommonDTO maReportCommonDTO, String webRoot) throws Exception
    {
        String excelPath = maReportCommonDTO.getFilePath();
        String foFilePath = webRoot + "dream\\report\\" + FileUtil.getFileName(excelPath) + ".fo";
        Excel2XslFo.convertExcel2PDF(excelPath,foFilePath);
    }
}

