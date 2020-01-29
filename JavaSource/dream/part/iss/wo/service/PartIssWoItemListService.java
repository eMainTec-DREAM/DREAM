package dream.part.iss.wo.service;

import java.rmi.RemoteException;
import java.util.List;

import javax.xml.rpc.ServiceException;

import common.bean.User;
import dream.part.iss.wo.dto.PartIssWoItemListDTO;

/**
 * �������Ȯ�� - ��� service
 * @author  hyosung
 * @version $Id:$
 * @since   1.0
 */
public interface PartIssWoItemListService
{     
    /**
     * grid find
     * @author  ssong
     * @version $Id:$
     * @param partIssWoItemListDTO
     * @since   1.0
     * 
     * @return ��ȸ ��� 
     * @throws ServiceException 
     * @throws RemoteException 
     * @throws Exception
     */
    public List findPtIssList(PartIssWoItemListDTO partIssWoItemListDTO,User user) throws RemoteException, ServiceException;

    public int deleteKey(String[] deleteRows);


}
