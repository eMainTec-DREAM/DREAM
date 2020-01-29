package dream.tool.adj.service.spring;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import common.bean.User;
import dream.tool.adj.dao.MaPttDisDetailDAO;
import dream.tool.adj.dto.MaPttDisDetailDTO;
import dream.tool.adj.service.MaPttDisDetailService;

/**
 * 공기구반납 - 상세 serviceimpl 
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="maPttDisDetailServiceTarget"
 * @spring.txbn id="maPttDisDetailService"
 * @spring.property name="maPttDisDetailDAO" ref="maPttDisDetailDAO"
 */
public class MaPttDisDetailServiceImpl implements MaPttDisDetailService
{
    private MaPttDisDetailDAO maPttDisDetailDAO = null;
    
    public MaPttDisDetailDAO getMaPttDisDetailDAO() 
    {
		return maPttDisDetailDAO;
	}

	public void setMaPttDisDetailDAO(MaPttDisDetailDAO maPttDisDetailDAO) 
	{
		this.maPttDisDetailDAO = maPttDisDetailDAO;
	}

	public MaPttDisDetailDTO findDetail(User user, String ptdisuseListId)throws Exception
    {
        MaPttDisDetailDTO maPttDisDetailDTO = maPttDisDetailDAO.findDetail(user, ptdisuseListId);
        
        return maPttDisDetailDTO;
    }
	
	public int insertDetail(MaPttDisDetailDTO maPttDisDetailDTO) throws Exception
    {   
        return maPttDisDetailDAO.insertDetail(maPttDisDetailDTO);
    }
	
	public int updateDetail(MaPttDisDetailDTO maPttDisDetailDTO) throws Exception
    {   
	    int resultCnt = 0;
	    String compNo = maPttDisDetailDTO.getCompNo();
	    String ptdisuseListId = maPttDisDetailDTO.getPtdisuselistId();
	    
	    // 상태가 반납완료[C]가 아닌 경우만 Update 한다. 
        String ptDisListStatus = maPttDisDetailDAO.findPtDisListStatus(compNo, ptdisuseListId);
        if(!"C".equals(ptDisListStatus))
        {
	        resultCnt = maPttDisDetailDAO.updateDetail(maPttDisDetailDTO);
	    }
	    
        return resultCnt;
    }
	
	public String[] disPart(MaPttDisDetailDTO maPttDisDetailDTO, User user) throws Exception
    {
        /** @param bwart 이동유형 201
         * @param budat 전기일 20160726
         * @param werks 플랜트 S111
         * @param lgort 저장위치 1190
         * @param gsber 사업영역 S110
         * @param kostl 코스트센터 현업문의-->  S21032
         * @param matnr 품번 BDMZ-00061
         * @param menge 수량 5
         * @param meins 수량단위 EA
         */
        String rtnValue = "S";
        String[] rtnArr = new String[4];

        //Part Grade :B 면 ERP 호출 없이 출고 진행

        maPttDisDetailDTO.setPtDisStatus("C");
        maPttDisDetailDAO.updatePtDisList(maPttDisDetailDTO);

        //String ptisshistId = maPttDisDetailDAO.getNextSequence("SQAPTDISHIST_ID");
        //maPttDisDetailDAO.insertPtIssHist(maPttDisDetailDTO, ptisshistId, "C");

        //재고수량조정
//        maPttDisDetailDAO.updatePtStock(maPttDisDetailDTO);
//        //TAPTRENT_STOCK 수정
//        maPttDisDetailDAO.updatePtRentStock(maPttDisDetailDTO);
        
        Map<String, Object> reportMap = null;
        List<Map<String, Object>> reportList = new ArrayList<Map<String, Object>>();
        List itemList = maPttDisDetailDAO.findItemList(maPttDisDetailDTO);
        
        if(itemList.size()==0)
        {
        	rtnArr[0] = "F";
        }
        else
        {
        	for(Iterator it = itemList.iterator(); it.hasNext();)
            {
            	Map hashMap = (Map) it.next();
                
                String partId = hashMap.get("PARTID").toString();
                String disQty = hashMap.get("DISQTY").toString();
                String wcodeId = hashMap.get("WCODEID").toString();
                String compNo = hashMap.get("COMPNO").toString();
                //maPttDisDetailDAO.updatePtStock(partId,disQty,wcodeId,compNo);
                
               
            }
        	 String ptisshistId = maPttDisDetailDAO.getNextSequence("SQAPTISSHIST_ID"); 
             maPttDisDetailDAO.insertPtIssHist(ptisshistId,maPttDisDetailDTO, "C");
             maPttDisDetailDAO.executeSpPtOutStock(maPttDisDetailDTO.getCompNo(), ptisshistId);
            rtnArr[0] = "S";
        }
        return rtnArr;
    }
    
}
