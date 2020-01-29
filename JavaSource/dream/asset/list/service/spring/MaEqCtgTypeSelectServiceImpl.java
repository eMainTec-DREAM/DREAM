package dream.asset.list.service.spring;

import java.util.List;

import common.bean.User;
import dream.asset.list.dao.MaEqCtgTypeSelectDAO;
import dream.asset.list.dto.MaEqCtgTypeSelectDTO;
import dream.asset.list.service.MaEqCtgTypeSelectService;

/**
 * ¼³ºñÀ¯Çü ÆË¾÷ ServiceImpl
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 *
 * @spring.bean id="maEqCtgTypeSelectServiceTarget"
 * @spring.txbn id="maEqCtgTypeSelectService"
 * @spring.property name="maEqCtgTypeSelectDAO" ref="maEqCtgTypeSelectDAO"
 */
public class MaEqCtgTypeSelectServiceImpl implements MaEqCtgTypeSelectService
{
    /** ÆË¾÷ DAO */
    private MaEqCtgTypeSelectDAO maEqCtgTypeSelectDAO = null;
    public MaEqCtgTypeSelectDAO getMaEqCtgTypeSelectDAO() 
    {
		return maEqCtgTypeSelectDAO;
	}

	public void setMaEqCtgTypeSelectDAO(MaEqCtgTypeSelectDAO maEqCtgTypeSelectDAO) 
	{
		this.maEqCtgTypeSelectDAO = maEqCtgTypeSelectDAO;
	}

	public List findList(User loginUser, MaEqCtgTypeSelectDTO maEqCtgTypeSelectDTO)
    {
        List resultList = null;
        resultList = maEqCtgTypeSelectDAO.findList(loginUser,maEqCtgTypeSelectDTO);
        
        return resultList;
    }
}