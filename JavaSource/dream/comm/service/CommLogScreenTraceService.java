package dream.comm.service;

import common.bean.User;

import dream.comm.dto.CommLogScrnTraceDTO;


/**
 * AutoComplete Service
 * @author  javaworker
 * @version $Id: ValidationService.java,v 1.1 2013/08/30 09:10:52 javaworker Exp $
 * @since   1.0
 *
 */
public interface CommLogScreenTraceService
{
    
	
	public int insertLogScreenTrace(CommLogScrnTraceDTO commLogScrnTraceDTO, User user);
    
    
}
