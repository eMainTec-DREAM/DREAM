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
 * ���ⱸ�ݳ� - �� serviceimpl 
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
	    
	    // ���°� �ݳ��Ϸ�[C]�� �ƴ� ��츸 Update �Ѵ�. 
        String ptDisListStatus = maPttDisDetailDAO.findPtDisListStatus(compNo, ptdisuseListId);
        if(!"C".equals(ptDisListStatus))
        {
	        resultCnt = maPttDisDetailDAO.updateDetail(maPttDisDetailDTO);
	    }
	    
        return resultCnt;
    }
	
	public String[] disPart(MaPttDisDetailDTO maPttDisDetailDTO, User user) throws Exception
    {
        /** @param bwart �̵����� 201
         * @param budat ������ 20160726
         * @param werks �÷�Ʈ S111
         * @param lgort ������ġ 1190
         * @param gsber ������� S110
         * @param kostl �ڽ�Ʈ���� ��������-->  S21032
         * @param matnr ǰ�� BDMZ-00061
         * @param menge ���� 5
         * @param meins �������� EA
         */
        String rtnValue = "S";
        String[] rtnArr = new String[4];

        //Part Grade :B �� ERP ȣ�� ���� ��� ����

        maPttDisDetailDTO.setPtDisStatus("C");
        maPttDisDetailDAO.updatePtDisList(maPttDisDetailDTO);

        //String ptisshistId = maPttDisDetailDAO.getNextSequence("SQAPTDISHIST_ID");
        //maPttDisDetailDAO.insertPtIssHist(maPttDisDetailDTO, ptisshistId, "C");

        //����������
//        maPttDisDetailDAO.updatePtStock(maPttDisDetailDTO);
//        //TAPTRENT_STOCK ����
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
