package intf.common.batch.dto;

import java.util.Map;

import common.bean.User;

/**
 * Common Batch DTO
 * @author  
 * @version $Id: $
 * @since   1.0
 * 
 */
public class CommonBatchDTO
{
    public static int BATCH = 1;
    public static int MANUAL = 2;
    
	/** Exec Type */
	private int execType = this.BATCH;
	/** Exec parameters */
	private Map arguments = null;
	/** Exec User */
	private User user = null;

    public int getExecType()
    {
        return execType;
    }

    public void setExecType(int execType)
    {
        this.execType = execType;
    }

    public Map getArguments()
    {
        return arguments;
    }

    public void setArguments(Map arguments)
    {
        this.arguments = arguments;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }
    
}
