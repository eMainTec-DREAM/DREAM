package dream.part.rep.service.spring;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import common.bean.User;
import dream.part.rep.dao.MaPtRepListDAO;
import dream.part.rep.dto.MaPtRepCommonDTO;
import dream.part.rep.dto.MaPtRepDetailDTO;
import dream.part.rep.service.MaPtRepDetailService;
import dream.part.rep.service.MaPtRepListService;

/**
 * 何前荐府 - 格废 serviceimpl
 * @author ssong
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="maPtRepListServiceTarget"
 * @spring.txbn id="maPtRepListService"
 * @spring.property name="maPtRepListDAO" ref="maPtRepListDAO"
 * @spring.property name="maPtRepDetailService" ref="maPtRepDetailService"
 */
public class MaPtRepListServiceImpl implements MaPtRepListService
{
    private MaPtRepListDAO maPtRepListDAO = null;
    private MaPtRepDetailService maPtRepDetailService = null;

    public MaPtRepDetailService getMaPtRepDetailService()
    {
        return maPtRepDetailService;
    }

    public void setMaPtRepDetailService(MaPtRepDetailService maPtRepDetailService)
    {
        this.maPtRepDetailService = maPtRepDetailService;
    }

    public MaPtRepListDAO getMaPtRepListDAO() 
    {
        return maPtRepListDAO;
    }

    public void setMaPtRepListDAO(MaPtRepListDAO maPtRepListDAO) 
    {
        this.maPtRepListDAO = maPtRepListDAO;
    }

    public List findList(MaPtRepCommonDTO maPtRepCommonDTO,User user)
    {      
        return maPtRepListDAO.findList(maPtRepCommonDTO,user);
    }
    
    public int deleteList(String compNo, String[] deleteRows) throws Exception
    {
        int result = 0;

        for(int i = 0; deleteRows.length > i ; i++)
        {
            result = result + maPtRepListDAO.deleteList(compNo, deleteRows[i]);
        }
        
        return result;
    }

	@Override
	public String findTotalCount(MaPtRepCommonDTO maPtRepCommonDTO, User user)
	{
		return maPtRepListDAO.findTotalCount(maPtRepCommonDTO, user);
	}

    @Override
    public int insertPtRepList(MaPtRepDetailDTO maPtRepDetailDTO, User user) throws Exception
    {
        int result = 0;
        
        JSONParser parser = new JSONParser();
        JSONArray array = (JSONArray) parser.parse(maPtRepDetailDTO.getMultiDesc());
        
        for(Object obj:array){
            JSONObject object = (JSONObject) obj;
            String seq = maPtRepListDAO.getNextSequence("SQAPTREPAIRLIST_ID");
            maPtRepDetailDTO.setPtRepairListId(seq);
            maPtRepDetailDTO.setPtRepairListNo(seq);
            maPtRepDetailDTO.setPartId((String) object.get("PART_ID"));
            maPtRepDetailDTO.setPartNo((String) object.get("PART_NO"));
            maPtRepDetailDTO.setPartNameSize((String) object.get("PARTNAMESIZE"));
            maPtRepDetailDTO.setIsSerial((String) object.get("IS_SERIAL_PART"));
            
            result = result + maPtRepDetailService.insertDetail(maPtRepDetailDTO, user);
        }
        
        return result;
    }

}