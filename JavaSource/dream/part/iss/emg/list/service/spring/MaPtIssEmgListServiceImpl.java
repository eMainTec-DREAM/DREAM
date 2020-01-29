package dream.part.iss.emg.list.service.spring;

import java.rmi.RemoteException;
import java.util.List;

import javax.xml.rpc.ServiceException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import common.bean.User;
import dream.part.iss.emg.list.dao.MaPtIssEmgListDAO;
import dream.part.iss.emg.list.dto.MaPtIssEmgCommonDTO;
import dream.part.iss.emg.list.dto.MaPtIssEmgDetailDTO;
import dream.part.iss.emg.list.service.MaPtIssEmgDetailService;
import dream.part.iss.emg.list.service.MaPtIssEmgListService;

/**
 * 긴급출고 - 목록 serviceimpl
 * @author ssong
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="maPtIssEmgListServiceTarget"
 * @spring.txbn id="maPtIssEmgListService"
 * @spring.property name="maPtIssEmgListDAO" ref="maPtIssEmgListDAO"
 * @spring.property name="maPtIssEmgDetailService" ref="maPtIssEmgDetailService"
 */
public class MaPtIssEmgListServiceImpl implements MaPtIssEmgListService
{
    private MaPtIssEmgListDAO maPtIssEmgListDAO = null;
    private MaPtIssEmgDetailService maPtIssEmgDetailService = null;

    public MaPtIssEmgDetailService getMaPtIssEmgDetailService()
    {
        return maPtIssEmgDetailService;
    }

    public void setMaPtIssEmgDetailService(
            MaPtIssEmgDetailService maPtIssEmgDetailService)
    {
        this.maPtIssEmgDetailService = maPtIssEmgDetailService;
    }

    public MaPtIssEmgListDAO getMaPtIssEmgListDAO() 
    {
		return maPtIssEmgListDAO;
	}

	public void setMaPtIssEmgListDAO(MaPtIssEmgListDAO maPtIssEmgListDAO) 
	{
		this.maPtIssEmgListDAO = maPtIssEmgListDAO;
	}

    public List findPtIssEmgList(MaPtIssEmgCommonDTO maPtIssEmgCommonDTO, User user) throws RemoteException, ServiceException
    {      
        return maPtIssEmgListDAO.findPtIssEmgList(maPtIssEmgCommonDTO,user);
    }

    @Override
    public int deleteKey(String[] deleteRows, User user)
    {
        int result = 0;
        
        if(!deleteRows.equals(null)){
            for(String ptisslistId : deleteRows)
            {
                result = result + maPtIssEmgListDAO.deletePtIssEmg(ptisslistId, user);
            }
        }
            
        
        return result;
    }

    @Override
    public String findTotalCount(MaPtIssEmgCommonDTO maPtIssEmgCommonDTO, User user)
    {
        return maPtIssEmgListDAO.findTotalCount(maPtIssEmgCommonDTO, user);
    }

    @Override
    public int insertPtIssEmgList(MaPtIssEmgDetailDTO maPtIssEmgDetailDTO, User user) throws Exception
    {
        int result = 0;
        
        JSONParser parser = new JSONParser();
        JSONArray array = (JSONArray) parser.parse(maPtIssEmgDetailDTO.getMultiDesc());
        
        for(Object obj:array){
            JSONObject object = (JSONObject) obj;
            String seq = maPtIssEmgListDAO.getNextSequence("SQAPTEMGISSLIST_ID");
            maPtIssEmgDetailDTO.setPtemgisslistId(seq);
            maPtIssEmgDetailDTO.setPartId((String) object.get("PART_ID"));
            maPtIssEmgDetailDTO.setPartNo((String) object.get("PART_NO"));
            maPtIssEmgDetailDTO.setPartDesc((String) object.get("FULL_DESC"));
            
            result = result + maPtIssEmgDetailService.insertDetail(maPtIssEmgDetailDTO, user);
        }
        
        return result;
    }
	
}

