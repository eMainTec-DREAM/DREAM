package dream.part.stk.service.spring;

import java.rmi.RemoteException;
import java.util.List;

import javax.xml.rpc.ServiceException;

import common.bean.User;
import dream.part.stk.dao.PartStkSerialListDAO;
import dream.part.stk.dto.PartStkSerialListDTO;
import dream.part.stk.service.PartStkSerialListService;

/**
 * 자재재고 - 목록 serviceimpl
 * @author hyosung
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="partStkSerialListServiceTarget"
 * @spring.txbn id="partStkSerialListService"
 * @spring.property name="partStkSerialListDAO" ref="partStkSerialListDAO"
 */
public class PartStkSerialListServiceImpl implements PartStkSerialListService
{
    private PartStkSerialListDAO partStkSerialListDAO = null;

    public PartStkSerialListDAO getPartStkSerialListDAO()
    {
        return partStkSerialListDAO;
    }

    public void setPartStkSerialListDAO(PartStkSerialListDAO partStkSerialListDAO)
    {
        this.partStkSerialListDAO = partStkSerialListDAO;
    }

 
    public List findSerialList(PartStkSerialListDTO partStkSerialListDTO,User user) throws RemoteException, ServiceException
    {
    
        return partStkSerialListDAO.findSerialList(partStkSerialListDTO, user);
    }
    
    

 	
	
}

