package dream.tool.list.service.spring;

import java.util.List;

import common.bean.User;
import dream.tool.list.dao.MaPttMstrListDAO;
import dream.tool.list.dto.MaPttMstrCommonDTO;
import dream.tool.list.dto.MaPttMstrListDTO;
import dream.tool.list.service.MaPttMstrListService;

/**
 * 보전자재분류(마스터) - 목록 serviceimpl
 * @author ssong
 * @version
 * @since 1.0
 * 
 * @spring.bean id="maPttMstrListServiceTarget"
 * @spring.txbn id="maPttMstrListService"
 * @spring.property name="maPttMstrListDAO" ref="maPttMstrListDAO"
 */
public class MaPttMstrListServiceImpl implements MaPttMstrListService
{
    private MaPttMstrListDAO maPttMstrListDAO = null;

    public MaPttMstrListDAO getMaPttMstrListDAO() 
    {
		return maPttMstrListDAO;
	}

	public void setMaPttMstrListDAO(MaPttMstrListDAO maPttMstrListDAO) 
	{
		this.maPttMstrListDAO = maPttMstrListDAO;
	}

	public List findList(MaPttMstrCommonDTO maPttMstrCommonDTO, User loginUser)
    {      
        return maPttMstrListDAO.findList(maPttMstrCommonDTO, loginUser);
    }

	public int deleteList(String[] deleteRows, User loginUser) throws Exception
	{
        int result = 0;

        if(!deleteRows.equals(null))
        {
            MaPttMstrListDTO maPttMstrListDTO = null;
            for(String id : deleteRows)
            {
                maPttMstrListDTO = new MaPttMstrListDTO();
                maPttMstrListDTO.setPartId(id);
                // 첨부파일 자재별 거래처 삭제후 자재 삭제 
                result = result + maPttMstrListDAO.updateDeletePartsFlag(maPttMstrListDTO, loginUser);
            }
        }
        return result;
    }

	@Override
	public String findTotalCount(MaPttMstrCommonDTO maPttMstrCommonDTO, User user) {
		return maPttMstrListDAO.findTotalCount(maPttMstrCommonDTO, user);
	}
}

