package dream.consult.program.foreport.dao.oraImpl;

import common.spring.BaseJdbcDaoSupportOra;
import dream.consult.program.foreport.dao.MaReportDAO;
import dream.consult.program.foreport.dto.MaReportCommonDTO;

/**
 * 레포트변환 DAO
 * @author  kim21017
 * @version $Id: MaReportDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maReportDAOTarget"
 * @spring.txbn id="maReportDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaReportDAOOraImpl extends BaseJdbcDaoSupportOra implements MaReportDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaReportDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param loginId
     * @return MaReportCommonDTO
     */
	public MaReportCommonDTO findDetail(String loginId)
    {
		return null;
    }
	
}