package dream.part.stk.service;

import java.rmi.RemoteException;
import java.util.List;

import javax.xml.rpc.ServiceException;

import common.bean.User;
import dream.part.stk.dto.PartStkSerialListDTO;


/**
 * �������Ȯ�� - ��� service
 * @author  hyosung
 * @version $Id:$
 * @since   1.0
 */
public interface PartStkSerialListService
{     
    /**
     * grid find
     * @author  hyosungfindSerialList
     * @version $Id:$
     * @param partStkSerialListDTO
     * @since   1.0
     * 
     * @return ��ȸ ��� 
     * @throws ServiceException 
     * @throws RemoteException 
     * @throws Exception
     */
    public List findSerialList(PartStkSerialListDTO partStkSerialListDTO,User user) throws RemoteException, ServiceException;

    


}
