package dream.pers.priv.info.service.spring;

import java.util.ArrayList;
import java.util.List;

import common.bean.User;
import common.util.CommonUtil;
import org.apache.commons.beanutils.BeanUtils;
import dream.pers.priv.info.dao.PersPrivFilterValueDAO;
import dream.pers.priv.info.dto.PersPrivFilterValueDTO;
import dream.pers.priv.info.service.PersPrivFilterValueService;

/**
 * ¸ñ·Ï
 * @author euna0207
 * @version $Id$
 * @since 1.0
 * 
 * @spring.bean id="persPrivFilterValueServiceTarget"
 * @spring.txbn id="persPrivFilterValueService"
 * @spring.property name="persPrivFilterValueDAO" ref="persPrivFilterValueDAO"
 */
public class PersPrivFilterValueServiceImpl implements PersPrivFilterValueService
{
    private PersPrivFilterValueDAO persPrivFilterValueDAO = null;

    public PersPrivFilterValueDAO getPersPrivFilterValueDAO() {
		return persPrivFilterValueDAO;
	}

	public void setPersPrivFilterValueDAO(PersPrivFilterValueDAO persPrivFilterValueDAO) {
		this.persPrivFilterValueDAO = persPrivFilterValueDAO;
	}
	

    public List findList(PersPrivFilterValueDTO persPrivFilterValueDTO, User user) throws Exception
    {      
		String pageId = persPrivFilterValueDAO.findPageId(persPrivFilterValueDTO);
		persPrivFilterValueDTO.setPageId(pageId);
		
        return persPrivFilterValueDAO.findList(persPrivFilterValueDTO, user);
    } 
    
    public String findTotalCount(PersPrivFilterValueDTO persPrivFilterValueDTO, User user) throws Exception
    {       
    	return persPrivFilterValueDAO.findTotalCount(persPrivFilterValueDTO, user);
    }
    

	public int[] deleteList(String[] deleteRows, User user) throws Exception
    {
        int[] result = null;
        PersPrivFilterValueDTO persPrivFilterValueDTO = new PersPrivFilterValueDTO();
        List<PersPrivFilterValueDTO> list = new ArrayList<PersPrivFilterValueDTO>();
        
        if(!deleteRows.equals(null)) {
        	for(int i=0; i<deleteRows.length; i++) {
        		persPrivFilterValueDTO.setUsrFilterValueId(deleteRows[i]);
        		
        		list.add((PersPrivFilterValueDTO)BeanUtils.cloneBean(persPrivFilterValueDTO));
        	}
        	result = persPrivFilterValueDAO.deleteList(list, user);
        }
        
        return result;
    }

	@Override
	public int insertDetail(PersPrivFilterValueDTO persPrivFilterValueDTO, User user) throws Exception {
		
		if(CommonUtil.isNullCheck(persPrivFilterValueDTO.getUsrFilterValueId()))
			persPrivFilterValueDTO.setUsrFilterValueId(persPrivFilterValueDAO.getNextSequence("SQAUSRFILTERVALUE_ID"));

		String pageId = persPrivFilterValueDAO.findPageId(persPrivFilterValueDTO);
		persPrivFilterValueDTO.setPageId(pageId);
		
		if("Y".equals(persPrivFilterValueDTO.getIsDefault())) {
			persPrivFilterValueDAO.updatedefault(persPrivFilterValueDTO, user);
		}
		
		List<PersPrivFilterValueDTO> list = new ArrayList<PersPrivFilterValueDTO>();
		list.add(persPrivFilterValueDTO);
		
		int[] rtn =  this.insertDetail(list, user);

		return rtn[0];
		
	}
	
	public int[] insertDetail(List<PersPrivFilterValueDTO> list, User user) throws Exception
    {
        return persPrivFilterValueDAO.insertDetail(list, user);
    }

}

