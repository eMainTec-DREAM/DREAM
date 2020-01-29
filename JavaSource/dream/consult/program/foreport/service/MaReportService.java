package dream.consult.program.foreport.service;

import dream.consult.program.foreport.dto.MaReportCommonDTO;

/**
 * 레포트변환 service
 * @author  kim21017
 * @version $Id: MaReportService.java,v 1.0 2015/12/02 09:12:40 kim21017 Exp $
 * @since   1.0
 */
public interface MaReportService
{     
	/**
     * Convert Excel to XSL-FO
     * @param maReportCommonDTO
     */
    public void createXslFo(MaReportCommonDTO maReportCommonDTO, String webRoot) throws Exception;

}
