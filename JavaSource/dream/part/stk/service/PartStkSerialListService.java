package dream.part.stk.service;

import java.rmi.RemoteException;
import java.util.List;

import javax.xml.rpc.ServiceException;

import common.bean.User;
import dream.part.stk.dto.PartStkSerialListDTO;


/**
 * 자재출고확정 - 목록 service
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
     * @return 조회 결과 
     * @throws ServiceException 
     * @throws RemoteException 
     * @throws Exception
     */
    public List findSerialList(PartStkSerialListDTO partStkSerialListDTO,User user) throws RemoteException, ServiceException;

    


}
