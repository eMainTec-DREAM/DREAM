package dream.part.iss.wo.service.spring;

import java.rmi.RemoteException;
import java.util.List;

import javax.xml.rpc.ServiceException;

import common.bean.User;
import dream.part.iss.wo.dao.PartIssWoItemListDAO;
import dream.part.iss.wo.dto.PartIssWoItemListDTO;
import dream.part.iss.wo.service.PartIssWoItemListService;

/**
 * 자재재고 - 목록 serviceimpl
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
	 * ERP 자재 
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
//	    //해당 I/F EndPoint 주소 정보설정
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
//	    header.setI_LGORT(binNo);  //보전저장위치
//	    header.setI_WERKS(wcodeNo);  //플랜트
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
//	       //재고 업데이트!
//	       //maPtIssDetailDAO.updateStock(body);
//	       
//	       //자재출고 이력 업데이트
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
	 * @param compNo 회사코드
	 * @param wCodeNo 플랜트
	 * @param binNo 저장위치
	 * @param partNo 품번
	 * @throws RemoteException 
	 * @throws ServiceException 
	 */
	/*public void partStockIfWhErp(String compNo, String wCodeNo, String binNo, String partNo) throws RemoteException, ServiceException
	{
	        Dymos_GMMSM000GMMSStorageStockSrcAdptGMMS_WS_StorageStockLocator locator = new Dymos_GMMSM000GMMSStorageStockSrcAdptGMMS_WS_StorageStockLocator();
	        //해당 I/F EndPoint 주소 정보설정
//	      locator.setDymos_GMMS_M000_GMMS_PartDeliveryHist_Src_adpt_GMMS_WS_PartDeliveryHist_PortEndpointAddress("http://10.214.100.124:5520/ws/Dymos_GMMS.M000.GMMS.PartDeliveryHist.Src.adpt.GMMS_WS_PartDeliveryHist/Dymos_GMMS_M000_GMMS_PartDeliveryHist_Src_adpt_GMMS_WS_PartDeliveryHist_Port"); 
	        locator.setDymos_GMMS_M000_GMMS_StorageStock_Src_adpt_GMMS_WS_StorageStock_PortEndpointAddress("http://"+MwareConfig.getIfErpUrl()+"/ws/Dymos_GMMS.M000.GMMS.StorageStock.Src.adpt.GMMS_WS_StorageStock/Dymos_GMMS_M000_GMMS_StorageStock_Src_adpt_GMMS_WS_StorageStock_Port"); 

	        Dymos_GMMS_M000_GMMS_StorageStock_Src_adpt_GMMS_WS_StorageStock_BinderStub go
	        = (Dymos_GMMS_M000_GMMS_StorageStock_Src_adpt_GMMS_WS_StorageStock_BinderStub)locator.getDymos_GMMS_M000_GMMS_StorageStock_Src_adpt_GMMS_WS_StorageStock_Port();

	        go.setUsername(MwareConfig.getIfErpUserName());
	        go.setPassword(MwareConfig.getIfErpPassword());
	        
	        HEADER header = new HEADER();
	        
	        header.setI_LGORT(binNo);  //보전저장위치
	        header.setI_WERKS(wCodeNo);  //플랜트
	        header.setI_BUKRS(compNo);
	        header.setI_MATNR(partNo);
	        
//	        header.setI_LGORT("");  //보전저장위치
//            header.setI_WERKS("DAA03");  //플랜트
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
	           
	           //재고 업데이트!
	           maPtIssDetailDAO.updateStock(body);
	           
	        }
	}*/
	
	
}

