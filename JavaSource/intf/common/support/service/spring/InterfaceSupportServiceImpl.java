package intf.common.support.service.spring;

import java.util.List;

import intf.common.support.dao.InterfaceSupportDAO;
import intf.common.support.dto.InterfaceSupportDTO;
import intf.common.support.service.InterfaceSupportService;

/**
 * Interface Supporter
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 *
 * @spring.bean id="interfaceSupportServiceTarget"
 * @spring.txbn id="interfaceSupportService"
 * 
 * @spring.property name="interfaceSupportDAO" ref="interfaceSupportDAO"
 */
public class InterfaceSupportServiceImpl implements InterfaceSupportService
{
    /** interface DAO */
	private InterfaceSupportDAO interfaceSupportDAO = null;
	 
	public InterfaceSupportDAO getInterfaceSupportDAO()
    {
        return interfaceSupportDAO;
    }

    public void setInterfaceSupportDAO(InterfaceSupportDAO interfaceSupportDAO)
    {
        this.interfaceSupportDAO = interfaceSupportDAO;
    }

    @Override
    public boolean isExecIntf(InterfaceSupportDTO interfaceSupportDTO)
    {
        return interfaceSupportDAO.isExecIntf(interfaceSupportDTO);
    }

    @Override
    public int logExeData(InterfaceSupportDTO interfaceSupportDTO)
    {
        int rtn = interfaceSupportDAO.updateInft(interfaceSupportDTO);
        
        String exeLog = interfaceSupportDTO.getExeLog();
        interfaceSupportDTO.setIntfLogId(interfaceSupportDAO.getNextSequence("SQAINTFLOG_ID"));
        if(exeLog.length() > 1000)
        {
            int cnt = exeLog.length() / 1000;
            String sTstr = null;
            int bIdx = 0;
            int eIdx = 0;
            for(int i = 0 ; i <= cnt ; i++)
            {
                bIdx = i * 1000;
                eIdx = (i+1)* 1000 - 1;
                
                if(eIdx > exeLog.length()) eIdx = exeLog.length();
                
                sTstr = exeLog.substring(bIdx, eIdx);
                interfaceSupportDTO.setExeLog(sTstr);
                
                if(i == 0)
                    interfaceSupportDAO.insertIntfLog(interfaceSupportDTO);
                else
                    interfaceSupportDAO.updateIntfLog(interfaceSupportDTO);
            }
        }
        else
            interfaceSupportDAO.insertIntfLog(interfaceSupportDTO);
        
        return rtn;
    }

	@Override
	public String makeExecLogStr(Exception ex, String str) {
		StringBuffer sb = new StringBuffer();
		sb.append("\n");
		if(ex == null){
			sb.append(str);
		}else{
			sb.append("\n==================START : "+str+"==================");
			sb.append("\n"+ex.getMessage());
			sb.append("\n==================END   : "+str+"==================");
		}
		
		return sb.toString();
	}

	@Override
	public String getLastSuccessDate(String compNo, String intfNo) {
		return interfaceSupportDAO.getLastSuccessDate(compNo, intfNo);
	}
	
	@Override
	public String getLastSuccessTime(String compNo, String intfNo) {
	    return interfaceSupportDAO.getLastSuccessTime(compNo, intfNo);
	}

    @Override
    public List getAccData(InterfaceSupportDTO interfaceSupportDTO)
    {
        return interfaceSupportDAO.getAccData(interfaceSupportDTO);
    }
}
