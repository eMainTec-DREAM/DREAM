package dream.part.iss.wo.service.spring;

import java.rmi.RemoteException;
import java.util.List;

import javax.xml.rpc.ServiceException;

import common.bean.User;
import dream.part.iss.wo.dao.PartIssWoItemListDAO;
import dream.part.iss.wo.dto.PartIssWoItemListDTO;
import dream.part.iss.wo.service.PartIssWoItemListService;

/**
 * ������� - ��� serviceimpl
 * @author ssong
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="partIssWoItemListServiceTarget"
 * @spring.txbn id="partIssWoItemListService"
 * @spring.property name="partIssWoItemListDAO" ref="partIssWoItemListDAO"
 */
public class PartIssWoItemListServiceImpl implements PartIssWoItemListService
{
    private PartIssWoItemListDAO partIssWoItemListDAO = null;

   

    public PartIssWoItemListDAO getPartIssWoItemListDAO()
    {
        return partIssWoItemListDAO;
    }

    public void setPartIssWoItemListDAO(PartIssWoItemListDAO partIssWoItemListDAO)
    {
        this.partIssWoItemListDAO = partIssWoItemListDAO;
    }

    public List findPtIssList(PartIssWoItemListDTO partIssWoItemListDTO, User user) throws RemoteException, ServiceException
    {      
        return partIssWoItemListDAO.findPtIssList(partIssWoItemListDTO,user);
    }

    @Override
    public int deleteKey(String[] deleteRows)
    {
        int result = 0;
        
        if(!deleteRows.equals(null)){
            for(String ptisslistSerialId : deleteRows)
            {
                result = result + partIssWoItemListDAO.deletePtIss(ptisslistSerialId);
            }
            
        }
            
        
        return result;
    }
	
	/**
	 * ERP ���� 
	 * @author  jung7126
	 * @version $Id:$
	 * @since   1.0
	 * 
	 * @param wcodeNo
	 * @param binNo
	 * @throws ServiceException
	 * @throws RemoteException
	 */
//	public void partIssHistIfWhErp(String wcodeNo, String binNo) throws ServiceException, RemoteException
//	{
//	    Dymos_GMMSM000GMMSPartDeliveryHistSrcAdptGMMS_WS_PartDeliveryHistLocator locator = new Dymos_GMMSM000GMMSPartDeliveryHistSrcAdptGMMS_WS_PartDeliveryHistLocator();
//	    //�ش� I/F EndPoint �ּ� ��������
////	    locator.setDymos_GMMS_M000_GMMS_PartDeliveryHist_Src_adpt_GMMS_WS_PartDeliveryHist_PortEndpointAddress("http://10.214.100.124:5520/ws/Dymos_GMMS.M000.GMMS.PartDeliveryHist.Src.adpt.GMMS_WS_PartDeliveryHist/Dymos_GMMS_M000_GMMS_PartDeliveryHist_Src_adpt_GMMS_WS_PartDeliveryHist_Port"); 
//	    locator.setDymos_GMMS_M000_GMMS_PartDeliveryHist_Src_adpt_GMMS_WS_PartDeliveryHist_PortEndpointAddress("http://"+MwareConfig.getIfErpUrl()+"/ws/Dymos_GMMS.M000.GMMS.PartDeliveryHist.Src.adpt.GMMS_WS_PartDeliveryHist/Dymos_GMMS_M000_GMMS_PartDeliveryHist_Src_adpt_GMMS_WS_PartDeliveryHist_Port"); 
//
//	    Dymos_GMMS_M000_GMMS_PartDeliveryHist_Src_adpt_GMMS_WS_PartDeliveryHist_BinderStub go
//        = (Dymos_GMMS_M000_GMMS_PartDeliveryHist_Src_adpt_GMMS_WS_PartDeliveryHist_BinderStub)locator.getDymos_GMMS_M000_GMMS_PartDeliveryHist_Src_adpt_GMMS_WS_PartDeliveryHist_Port();
//
//	    go.setUsername(MwareConfig.getIfErpUserName());
//	    go.setPassword(MwareConfig.getIfErpPassword());
//	    
//	    HEADER header = new HEADER();
//	    
//	    header.setI_LGORT(binNo);  //����������ġ
//	    header.setI_WERKS(wcodeNo);  //�÷�Ʈ
//	    
//	    HEADER[] arr = {header};
//	       
//	    GMMS_PartDeliveryHistResponse resp = go.GMMS_PartDeliveryHist(arr);
//	    
//	    System.out.println("=================INTERFACE=================");
//	    System.out.println("RESULT TYPE    : "+resp.getRESULT().getO_RTNTYP());
//	    System.out.println("RESULT MESSAGE : "+resp.getRESULT().getO_RTNMSG());
//	    System.out.println("===========================================");
//	    
//	    BODY[] bodyArr = resp.getBODY();
//
//	    for(BODY body : bodyArr)
//	    {
//	       if(body == null) continue;
//	       
//	       //��� ������Ʈ!
//	       //maPtIssDetailDAO.updateStock(body);
//	       
//	       //������� �̷� ������Ʈ
//	       maPtIssDetailDAO.updatePtIssHist(body);
//	       
//	    }
//
//	}
	
	/**
	 * @author  jung7126
	 * @version $Id:$
	 * @since   1.0
	 * 
	 * @param compNo ȸ���ڵ�
	 * @param wCodeNo �÷�Ʈ
	 * @param binNo ������ġ
	 * @param partNo ǰ��
	 * @throws RemoteException 
	 * @throws ServiceException 
	 */
	/*public void partStockIfWhErp(String compNo, String wCodeNo, String binNo, String partNo) throws RemoteException, ServiceException
	{
	        Dymos_GMMSM000GMMSStorageStockSrcAdptGMMS_WS_StorageStockLocator locator = new Dymos_GMMSM000GMMSStorageStockSrcAdptGMMS_WS_StorageStockLocator();
	        //�ش� I/F EndPoint �ּ� ��������
//	      locator.setDymos_GMMS_M000_GMMS_PartDeliveryHist_Src_adpt_GMMS_WS_PartDeliveryHist_PortEndpointAddress("http://10.214.100.124:5520/ws/Dymos_GMMS.M000.GMMS.PartDeliveryHist.Src.adpt.GMMS_WS_PartDeliveryHist/Dymos_GMMS_M000_GMMS_PartDeliveryHist_Src_adpt_GMMS_WS_PartDeliveryHist_Port"); 
	        locator.setDymos_GMMS_M000_GMMS_StorageStock_Src_adpt_GMMS_WS_StorageStock_PortEndpointAddress("http://"+MwareConfig.getIfErpUrl()+"/ws/Dymos_GMMS.M000.GMMS.StorageStock.Src.adpt.GMMS_WS_StorageStock/Dymos_GMMS_M000_GMMS_StorageStock_Src_adpt_GMMS_WS_StorageStock_Port"); 

	        Dymos_GMMS_M000_GMMS_StorageStock_Src_adpt_GMMS_WS_StorageStock_BinderStub go
	        = (Dymos_GMMS_M000_GMMS_StorageStock_Src_adpt_GMMS_WS_StorageStock_BinderStub)locator.getDymos_GMMS_M000_GMMS_StorageStock_Src_adpt_GMMS_WS_StorageStock_Port();

	        go.setUsername(MwareConfig.getIfErpUserName());
	        go.setPassword(MwareConfig.getIfErpPassword());
	        
	        HEADER header = new HEADER();
	        
	        header.setI_LGORT(binNo);  //����������ġ
	        header.setI_WERKS(wCodeNo);  //�÷�Ʈ
	        header.setI_BUKRS(compNo);
	        header.setI_MATNR(partNo);
	        
//	        header.setI_LGORT("");  //����������ġ
//            header.setI_WERKS("DAA03");  //�÷�Ʈ
//            header.setI_BUKRS("100");
//            header.setI_MATNR("");
	        
	        HEADER[] arr = {header};
	           
	        GMMS_StorageStockResponse resp = go.GMMS_StorageStock(arr);
	        
	        System.out.println("=================INTERFACE=================");
	        System.out.println("RESULT TYPE    : "+resp.getRESULT().getO_RTNTYP());
	        System.out.println("RESULT MESSAGE : "+resp.getRESULT().getO_RTNMSG());
	        System.out.println("===========================================");
	        
	        BODY[] bodyArr = resp.getBODY();

	        System.out.println("!!!!"+bodyArr.length);
	        for(BODY body : bodyArr)
	        {
	           if(body == null) continue;
	           
	           //��� ������Ʈ!
	           maPtIssDetailDAO.updateStock(body);
	           
	        }
	}*/
	
	
}

