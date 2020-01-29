package dream.mgr.user.service.spring;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.util.CommonUtil;
import dream.mgr.user.dao.MgrUserPlantauthListDAO;
import dream.mgr.user.dto.MaUserCommonDTO;
import dream.mgr.user.dto.MgrUserPlantauthDetailDTO;
import dream.mgr.user.service.MgrUserPlantauthDetailService;
import dream.mgr.user.service.MgrUserPlantauthListService;

/**
 * 사용자 - 목록 serviceimpl
 * @author 
 * @version $Id: $
 * @since 1.0
 * 
 * @spring.bean id="mgrUserPlantauthListServiceTarget"
 * @spring.txbn id="mgrUserPlantauthListService"
 * @spring.property name="mgrUserPlantauthListDAO" ref="mgrUserPlantauthListDAO"
 */
public class MgrUserPlantauthListServiceImpl implements MgrUserPlantauthListService
{
    private MgrUserPlantauthListDAO mgrUserPlantauthListDAO = null;

    public MgrUserPlantauthListDAO getMgrUserPlantauthListDAO() 
    {
		return mgrUserPlantauthListDAO;
	}

	public void setMgrUserPlantauthListDAO(MgrUserPlantauthListDAO mgrUserPlantauthListDAO) 
	{
		this.mgrUserPlantauthListDAO = mgrUserPlantauthListDAO;
	}

	public List findUserList(MaUserCommonDTO maUserCommonDTO, User loginUser)
    {      
	    return mgrUserPlantauthListDAO.findUserList(maUserCommonDTO, loginUser);
    }
	public String findTotalCount(MaUserCommonDTO maUserCommonDTO, User loginUser)
    {
        return mgrUserPlantauthListDAO.findTotalCount(maUserCommonDTO, loginUser);
    }
	public int deleteList(String[] deleteRows, User loginUser) throws Exception
    {
        int result = 0;
        
        if(!deleteRows.equals(null))
        {
            MaUserCommonDTO maUserCommonDTO = null;
            for(String id : deleteRows)
            {
                maUserCommonDTO = new MaUserCommonDTO();
                maUserCommonDTO.setUsrPlantauthId(id);
                result = result + mgrUserPlantauthListDAO.deleteUser(maUserCommonDTO, loginUser);
            }
        }
        
        return result;
    }

	public void savePointList(List<Map> gridList, User user) throws Exception 
	{
		MgrUserPlantauthDetailDTO mgrUserPlantauthDetailDTO = null;
		MaUserCommonDTO maUserCommonDTO = null;
		MgrUserPlantauthDetailService	mgrUserPlantauthDetailService = (MgrUserPlantauthDetailService)CommonUtil.getBean("mgrUserPlantauthDetailService", user.getCompNo());
		
		for(Map map : gridList)
        {
			mgrUserPlantauthDetailDTO = (MgrUserPlantauthDetailDTO)CommonUtil.makeDTO(map, MgrUserPlantauthDetailDTO.class);
			maUserCommonDTO = (MaUserCommonDTO)CommonUtil.makeDTO(map, MaUserCommonDTO.class);
			
			switch(mgrUserPlantauthDetailDTO.getNativeeditor())
			{
				case "inserted":
					break;
				case "updated" : mgrUserPlantauthDetailService.updateDetail(mgrUserPlantauthDetailDTO, user);
					break;
				case "deleted": mgrUserPlantauthListDAO.deleteUser(maUserCommonDTO, user);
					break;
			}
        }
	}

	@Override
	public int insertList(MaUserCommonDTO maUserCommonDTO, MgrUserPlantauthDetailDTO mgrUserPlantauthDetailDTO, User user) throws Exception 
	{
		int result = 0;
		
		String[] multiKey = mgrUserPlantauthDetailDTO.getMultiKey().split("\\+"); 
		MgrUserPlantauthDetailService	mgrUserPlantauthDetailService = (MgrUserPlantauthDetailService)CommonUtil.getBean("mgrUserPlantauthDetailService", user.getCompNo());
		
		for(int i=0; multiKey.length > i; i++)
		{
			mgrUserPlantauthDetailDTO.setPlant(multiKey[i]);
			mgrUserPlantauthDetailDTO.setUserId(maUserCommonDTO.getUserId());
			mgrUserPlantauthDetailDTO.setUsrplantauthId(mgrUserPlantauthListDAO.getNextSequence("SQAUSRPLANTAUTH_ID"));
			
			String cnt = mgrUserPlantauthDetailService.validPlant(maUserCommonDTO, mgrUserPlantauthDetailDTO, user, "Y");
			
			if("0".equals(cnt))
			{
				result = result + mgrUserPlantauthDetailService.insertDetail(mgrUserPlantauthDetailDTO, user);
			}
		}
		
		return result;
	}
}

