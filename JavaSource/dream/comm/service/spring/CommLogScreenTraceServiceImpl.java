package dream.comm.service.spring;

import common.bean.MwareConfig;
import common.bean.User;
import common.util.DateUtil;
import dream.comm.dao.CommLogScreenTraceDAO;
import dream.comm.dto.CommLogScrnTraceDTO;
import dream.comm.service.CommLogScreenTraceService;

/**
 * Validation ServiceImpl
 * @author  javaworker
 * @version $Id: ValidationServiceImpl.java,v 1.11 2014/08/05 01:14:19 pochul2423 Exp $
 * @since   1.0
 *
 * @spring.bean id="commLogScreenTraceServiceTarget"
 * @spring.txbn id="commLogScreenTraceService"
 * @spring.property name="commLogScreenTraceDAO" ref="commLogScreenTraceDAO"
 */
public class CommLogScreenTraceServiceImpl implements CommLogScreenTraceService
{
    /** Validation DAO */
    private CommLogScreenTraceDAO commLogScreenTraceDAO = null;
    
    public CommLogScreenTraceDAO getCommLogScreenTraceDAO() {
		return commLogScreenTraceDAO;
	}

	public void setCommLogScreenTraceDAO(CommLogScreenTraceDAO commLogScreenTraceDAO) {
		this.commLogScreenTraceDAO = commLogScreenTraceDAO;
	}



	public int insertLogScreenTrace(CommLogScrnTraceDTO commLogScrnTraceDTO, User user)
    {   
		int result=0;
		
		if ("Y".equals(MwareConfig.getIsUseScrntrace())){
			
			String scrnTraceLogId = commLogScreenTraceDAO.getNextSequence("SQASCRNTRACELOG_ID");
			
			commLogScrnTraceDTO.setScrnTraceLogId(scrnTraceLogId);
			commLogScrnTraceDTO.setUpdDate(DateUtil.getDateTime("yyyy-mm-dd HH:mm:ss"));
			
			commLogScreenTraceDAO.insertLogScreenTrace(commLogScrnTraceDTO, user);
			
		}
		return result;
    }

}

