package dream.part.list.service.spring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.bean.User;
import dream.part.list.dao.MaPtMstrListDAO;
import dream.part.list.dto.MaPtMstrCommonDTO;
import dream.part.list.dto.MaPtMstrListDTO;
import dream.part.list.service.MaPtMstrListService;

/**
 * 보전자재분류(마스터) - 목록 serviceimpl
 * @author ssong
 * @version
 * @since 1.0
 * 
 * @spring.bean id="maPtMstrListServiceTarget"
 * @spring.txbn id="maPtMstrListService"
 * @spring.property name="maPtMstrListDAO" ref="maPtMstrListDAO"
 */
public class MaPtMstrListServiceImpl implements MaPtMstrListService
{
    private MaPtMstrListDAO maPtMstrListDAO = null;

    public MaPtMstrListDAO getMaPtMstrListDAO() 
    {
		return maPtMstrListDAO;
	}

	public void setMaPtMstrListDAO(MaPtMstrListDAO maPtMstrListDAO) 
	{
		this.maPtMstrListDAO = maPtMstrListDAO;
	}

	public List findList(MaPtMstrCommonDTO maPtMstrCommonDTO, User loginUser)
    {      
        return maPtMstrListDAO.findList(maPtMstrCommonDTO, loginUser);
    }

	public int deleteList(String[] deleteRows, User loginUser) throws Exception
	{
        int result = 0;

        if(!deleteRows.equals(null))
        {
            MaPtMstrListDTO maPtMstrListDTO = null;
            for(String id : deleteRows)
            {
                maPtMstrListDTO = new MaPtMstrListDTO();
                maPtMstrListDTO.setPartId(id);
                // 첨부파일 자재별 거래처 삭제후 자재 삭제 
                result = result + maPtMstrListDAO.deleteStock(maPtMstrListDTO, loginUser);
                result = result + maPtMstrListDAO.deleteSaftyStock(maPtMstrListDTO, loginUser);
                result = result + maPtMstrListDAO.updateDeletePartsFlag(maPtMstrListDTO, loginUser);
            }
        }
        return result;
    }
	public List copyParts(String[] selectRows, User loginUser) throws Exception{
        int result = 0;
        List resultList = new ArrayList();
        Map map =  null;
        if(!selectRows.equals(null)){
            for(String id : selectRows)
            {
            	//새로운 시퀀스 받기.
        		String newSeq = maPtMstrListDAO.getPartsSeq();
        		map = new HashMap();
        		map.put("id", newSeq);
        		resultList.add(map);
                result = result + maPtMstrListDAO.insertCopyDetail(newSeq, id, loginUser);
            }
        }
        maPtMstrListDAO.SP_IF_UPD_TXPARTS(loginUser);
        
        return resultList;
    }
    @Override
    public String findTotalCount(MaPtMstrCommonDTO maPtMstrCommonDTO,User user)
    {
        return maPtMstrListDAO.findTotalCount(maPtMstrCommonDTO, user);
    }

	@Override
	public String getData(User user, MaPtMstrCommonDTO maPtMstrCommonDTO) {

		String type = "PART";
		maPtMstrCommonDTO.setExceltabNo(type);	
		
		return maPtMstrListDAO.getData(user, maPtMstrCommonDTO);
	}
}

