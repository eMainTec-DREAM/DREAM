package dream.part.adj.service.spring;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import common.bean.User;
import dream.part.adj.dao.MaPtFcRecDetailDAO;
import dream.part.adj.dao.MaPtFcRecListDAO;
import dream.part.adj.dto.MaPtFcRecCommonDTO;
import dream.part.adj.dto.MaPtFcRecDetailDTO;
import dream.part.adj.service.MaPtFcRecDetailService;
import dream.part.adj.service.MaPtFcRecListService;

/**
 * 무상입고 - 목록 serviceimpl
 * @author ssong
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="maPtFcRecListServiceTarget"
 * @spring.txbn id="maPtFcRecListService"
 * @spring.property name="maPtFcRecListDAO" ref="maPtFcRecListDAO"
 * @spring.property name="maPtFcRecDetailDAO" ref="maPtFcRecDetailDAO"
 * @spring.property name="maPtFcRecDetailService" ref="maPtFcRecDetailService"
 */
public class MaPtFcRecListServiceImpl implements MaPtFcRecListService
{
    private MaPtFcRecListDAO maPtFcRecListDAO = null;
    private MaPtFcRecDetailDAO maPtFcRecDetailDAO = null;
    private MaPtFcRecDetailService maPtFcRecDetailService = null;

    public MaPtFcRecDetailService getMaPtFcRecDetailService()
    {
        return maPtFcRecDetailService;
    }

    public void setMaPtFcRecDetailService(
            MaPtFcRecDetailService maPtFcRecDetailService)
    {
        this.maPtFcRecDetailService = maPtFcRecDetailService;
    }

    public MaPtFcRecListDAO getMaPtFcRecListDAO() {
		return maPtFcRecListDAO;
	}

	public void setMaPtFcRecListDAO(MaPtFcRecListDAO maPtFcRecListDAO) {
		this.maPtFcRecListDAO = maPtFcRecListDAO;
	}

	public MaPtFcRecDetailDAO getMaPtFcRecDetailDAO() {
		return maPtFcRecDetailDAO;
	}

	public void setMaPtFcRecDetailDAO(MaPtFcRecDetailDAO maPtFcRecDetailDAO) {
		this.maPtFcRecDetailDAO = maPtFcRecDetailDAO;
	}

	public List findList(MaPtFcRecCommonDTO maPtFcRecCommonDTO, User user)
    {      
        return maPtFcRecListDAO.findList(maPtFcRecCommonDTO,user);
    }
    
    public int deleteList(String compNo, String[] deleteRows) throws Exception
    {
        int result = 0;
        String ptFcRecListId = "";
        
        for(int i = 0; deleteRows.length > i ; i++)
        {
            ptFcRecListId = deleteRows[i];
            
            // 상태가 입고완료[C]가 아닌 경우만 Delete 한다. 
            String prFceRecListStatus = maPtFcRecDetailDAO.findFcRecListStatus(compNo, ptFcRecListId);
            if(!"C".equals(prFceRecListStatus))
            {
                result = result + maPtFcRecListDAO.deleteList(compNo, ptFcRecListId);
            }
        }
        
        return result;
    }

    @Override
    public int insertPtFcRec(MaPtFcRecDetailDTO maPtFcRecDetailDTO, User user) throws Exception
    {
        int result = 0;
        
        JSONParser parser = new JSONParser();
        JSONArray array = (JSONArray) parser.parse(maPtFcRecDetailDTO.getMultiDesc());
        
        for(Object obj:array){
            JSONObject object = (JSONObject) obj;
            String seq = maPtFcRecListDAO.getNextSequence("SQAFCRECLIST_ID");
            maPtFcRecDetailDTO.setFcRecListId(seq);
            maPtFcRecDetailDTO.setFcRecListNo(seq);
            maPtFcRecDetailDTO.setPartId((String) object.get("PART_ID"));
            maPtFcRecDetailDTO.setPartNo((String) object.get("PART_NO"));
            maPtFcRecDetailDTO.setPartNameSize((String) object.get("PARTNAMESIZE"));
            
            result = result + maPtFcRecDetailService.insertDetail(maPtFcRecDetailDTO);
        }
        
        return result;
    }

	@Override
	public String findTotalCount(MaPtFcRecCommonDTO maPtFcRecCommonDTO, User user) throws Exception {
		return maPtFcRecListDAO.findTotalCount(maPtFcRecCommonDTO, user);
	}

}