package dream.pers.priv.pgm.service.spring;

import java.util.List;

import common.bean.User;
import dream.pers.priv.pgm.dao.MaGrdUsrColListDAO;
import dream.pers.priv.pgm.dto.MaGrdUsrCommonDTO;
import dream.pers.priv.pgm.dto.MaGrdUsrDetailDTO;
import dream.pers.priv.pgm.service.MaGrdUsrColListService;

/**
 * Ä®·³ ¸ñ·Ï
 * @author jung7126
 * @version $Id: MaGrdUsrColListServiceImpl.java,v 1.0 2015/12/02 09:12:51 jung7126 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maGrdUsrColListServiceTarget"
 * @spring.txbn id="maGrdUsrColListService"
 * @spring.property name="maGrdUsrColListDAO" ref="maGrdUsrColListDAO"
 */
public class MaGrdUsrColListServiceImpl implements MaGrdUsrColListService
{
    private MaGrdUsrColListDAO maGrdUsrColListDAO = null;

	public MaGrdUsrColListDAO getMaGrdUsrColListDAO() {
		return maGrdUsrColListDAO;
	}

	public void setMaGrdUsrColListDAO(MaGrdUsrColListDAO maGrdUsrColListDAO) {
		this.maGrdUsrColListDAO = maGrdUsrColListDAO;
	}

    public List findColList(MaGrdUsrCommonDTO maGrdUsrCommonDTO,MaGrdUsrDetailDTO maGrdUsrDetailDTO, User user)
    {
        List resultList = null;
        resultList = maGrdUsrColListDAO.findUserColList(maGrdUsrDetailDTO, maGrdUsrCommonDTO, user);

        if(resultList.size() == 0) resultList= maGrdUsrColListDAO.findColList(maGrdUsrDetailDTO, maGrdUsrCommonDTO, user);
        
        return resultList;
    }

    @Override
    public String findTotalCount(MaGrdUsrCommonDTO maGrdUsrCommonDTO, MaGrdUsrDetailDTO maGrdUsrDetailDTO, User user)
    {
        String count = "";
        count = maGrdUsrColListDAO.findUserColCount(maGrdUsrDetailDTO, maGrdUsrCommonDTO, user);

        if(count.equals("0")) count= maGrdUsrColListDAO.findColCount(maGrdUsrDetailDTO, maGrdUsrCommonDTO, user);
        
        return count;
    }
	

}

