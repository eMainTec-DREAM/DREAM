package dream.part.iss.emg.req.service.spring;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import javax.xml.rpc.ServiceException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import common.bean.User;
import common.util.CommonUtil;
import dream.part.iss.emg.req.dao.PartIssEmgReqPartsListDAO;
import dream.part.iss.emg.req.dto.PartIssEmgReqCommonDTO;
import dream.part.iss.emg.req.dto.PartIssEmgReqPartsDetailDTO;
import dream.part.iss.emg.req.service.PartIssEmgReqPartsDetailService;
import dream.part.iss.emg.req.service.PartIssEmgReqPartsListService;

/**
 * 긴급출고 - 목록 serviceimpl
 * @author ssong
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="partIssEmgReqPartsListServiceTarget"
 * @spring.txbn id="partIssEmgReqPartsListService"
 * @spring.property name="partIssEmgReqPartsListDAO" ref="partIssEmgReqPartsListDAO"
 * @spring.property name="partIssEmgReqPartsDetailService" ref="partIssEmgReqPartsDetailService"
 */
public class PartIssEmgReqPartsListServiceImpl implements PartIssEmgReqPartsListService
{
    private PartIssEmgReqPartsListDAO partIssEmgReqPartsListDAO = null;
    private PartIssEmgReqPartsDetailService partIssEmgReqPartsDetailService = null;
    

    public PartIssEmgReqPartsDetailService getPartIssEmgReqPartsDetailService()
    {
        return partIssEmgReqPartsDetailService;
    }

    public void setPartIssEmgReqPartsDetailService(
            PartIssEmgReqPartsDetailService partIssEmgReqPartsDetailService)
    {
        this.partIssEmgReqPartsDetailService = partIssEmgReqPartsDetailService;
    }

    public List findPtIssEmgList(PartIssEmgReqCommonDTO partIssEmgReqCommonDTO,User user) throws RemoteException, ServiceException
    {      
        return partIssEmgReqPartsListDAO.findPtIssEmgList(partIssEmgReqCommonDTO,user);
    }

    @Override
    public int deleteKey(String[] deleteRows, User user)
    {
        int result = 0;
        
        if(!deleteRows.equals(null)){
            for(String ptisslistId : deleteRows)
            {
                result = result + partIssEmgReqPartsListDAO.deletePtIssEmg(ptisslistId, user);
            }
        }
            
        
        return result;
    }

    @Override
    public String findTotalCount(PartIssEmgReqCommonDTO partIssEmgReqCommonDTO,User user)
    {
        return partIssEmgReqPartsListDAO.findTotalCount(partIssEmgReqCommonDTO, user);
    }

    
    
	public PartIssEmgReqPartsListDAO getPartIssEmgReqPartsListDAO() {
		return partIssEmgReqPartsListDAO;
	}

	public void setPartIssEmgReqPartsListDAO(
			PartIssEmgReqPartsListDAO partIssEmgReqPartsListDAO) {
		this.partIssEmgReqPartsListDAO = partIssEmgReqPartsListDAO;
	}

    @Override
    public int insertPtIssEmgList(PartIssEmgReqPartsDetailDTO partIssEmgReqPartsDetailDTO, User user) throws Exception
    {
        int result = 0;
        
        JSONParser parser = new JSONParser();
        JSONArray array = (JSONArray) parser.parse(partIssEmgReqPartsDetailDTO.getMultiDesc());
        
        for(Object obj:array){
            JSONObject object = (JSONObject) obj;
            String seq = partIssEmgReqPartsListDAO.getNextSequence("SQAPTEMGISSLIST_ID");
            partIssEmgReqPartsDetailDTO.setPtEmgIssListId(seq);
            partIssEmgReqPartsDetailDTO.setPartId((String) object.get("PART_ID"));
            partIssEmgReqPartsDetailDTO.setPartNo((String) object.get("PART_NO"));
            partIssEmgReqPartsDetailDTO.setPartDesc((String) object.get("FULL_DESC"));
            
            result = result + partIssEmgReqPartsDetailService.insertDetail(partIssEmgReqPartsDetailDTO, user);
        }
        
        return result;
    }

    @Override
    public void saveList(List<Map> gridList, User user) throws Exception
    {
        PartIssEmgReqPartsDetailDTO partIssEmgReqPartsDetailDTO = null;
        
        for(Map map : gridList)
        {
            partIssEmgReqPartsDetailDTO = (PartIssEmgReqPartsDetailDTO)CommonUtil.makeDTO(map, PartIssEmgReqPartsDetailDTO.class);
            
            switch(partIssEmgReqPartsDetailDTO.getNativeeditor())
            {
            case "inserted":
                break;
            case "updated" : partIssEmgReqPartsDetailService.updateDetail(partIssEmgReqPartsDetailDTO, user);
                break;
            case "deleted" :
                break;
            }
        }
    }
    
}

