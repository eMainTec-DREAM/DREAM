package dream.part.iss.wo.service.spring;

import java.rmi.RemoteException;
import java.util.List;

import javax.xml.rpc.ServiceException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import common.bean.User;
import dream.part.iss.wo.dao.MaPtIssListDAO;
import dream.part.iss.wo.dto.MaPtIssCommonDTO;
import dream.part.iss.wo.dto.MaPtIssDetailDTO;
import dream.part.iss.wo.service.MaPtIssDetailService;
import dream.part.iss.wo.service.MaPtIssListService;

/**
 * 자재재고 - 목록 serviceimpl
 * @author ssong
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="maPtIssListServiceTarget"
 * @spring.txbn id="maPtIssListService"
 * @spring.property name="maPtIssListDAO" ref="maPtIssListDAO"
 * @spring.property name="maPtIssDetailService" ref="maPtIssDetailService"
 */
public class MaPtIssListServiceImpl implements MaPtIssListService
{
    private MaPtIssListDAO maPtIssListDAO = null;
    private MaPtIssDetailService maPtIssDetailService = null;

    public MaPtIssDetailService getMaPtIssDetailService()
    {
        return maPtIssDetailService;
    }

    public void setMaPtIssDetailService(MaPtIssDetailService maPtIssDetailService)
    {
        this.maPtIssDetailService = maPtIssDetailService;
    }

    public MaPtIssListDAO getMaPtIssListDAO() 
    {
		return maPtIssListDAO;
	}

	public void setMaPtIssListDAO(MaPtIssListDAO maPtIssListDAO) 
	{
		this.maPtIssListDAO = maPtIssListDAO;
	}

    public List findPtIssList(MaPtIssCommonDTO maPtIssCommonDTO, User user) throws RemoteException, ServiceException
    {      
        return maPtIssListDAO.findPtIssList(maPtIssCommonDTO,user);
    }
    public String findTotalCount(MaPtIssCommonDTO maPtIssCommonDTO,User user)
    {
        return maPtIssListDAO.findTotalCount(maPtIssCommonDTO, user);
    }
    
    @Override
    public int deleteKey(String[] deleteRows, User user)
    {
        int result = 0;
        int issQty = 0;
        if(!deleteRows.equals(null)){
            for(int i = 0; deleteRows.length > i; i++ )
            {
                String ptisslistId = deleteRows[i];
                
                issQty = 0;
                //완료되지 않은 출고만 삭제 
                issQty =  maPtIssListDAO.deletePtIss(ptisslistId, user);
                //출고가 삭제되었을때...
                if(issQty > 0)
                {
                    //WoPart와의 Link 제거 
                    maPtIssListDAO.unlinkWoParts(ptisslistId, user);
                    //삭제시 완료되지 않은 작업의 설비만 삭제한다.
                    maPtIssListDAO.deleteWoParts(ptisslistId, user);
                }
            }
        }
        
        return result;
    }

    @Override
    public int insertPtIssList(MaPtIssDetailDTO maPtIssDetailDTO, User user) throws Exception
    {
        int result = 0;
        
        JSONParser parser = new JSONParser();
        JSONArray array = (JSONArray) parser.parse(maPtIssDetailDTO.getMultiDesc());
        
        for(Object obj:array){
            JSONObject object = (JSONObject) obj;
            String seq = maPtIssListDAO.getNextSequence("SQAPTISSLIST_ID");
            maPtIssDetailDTO.setPtisslistId(seq);
            maPtIssDetailDTO.setPartId((String) object.get("PART_ID"));
            maPtIssDetailDTO.setPartNo((String) object.get("PART_NO"));
            maPtIssDetailDTO.setPartDesc((String) object.get("FULL_DESC"));
            maPtIssDetailDTO.setIsSerial((String) object.get("IS_SERIAL_PART"));
            
            result = result + maPtIssDetailService.insertDetail(maPtIssDetailDTO, user);
        }
        
        return result;
    }
	
}

