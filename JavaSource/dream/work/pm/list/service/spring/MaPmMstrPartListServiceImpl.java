package dream.work.pm.list.service.spring;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import common.bean.User;
import dream.work.pm.list.dao.MaPmMstrPartListDAO;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.MaPmMstrPartDetailDTO;
import dream.work.pm.list.service.MaPmMstrPartDetailService;
import dream.work.pm.list.service.MaPmMstrPartListService;

/**
 * 사용자재 목록
 * @author jung7126
 * @version $Id: MaPmMstrPartListServiceImpl.java,v 1.0 2015/12/02 09:12:51 jung7126 Exp $
 * @since 1.0
 * 
 * @spring.bean id="maPmMstrPartListServiceTarget"
 * @spring.txbn id="maPmMstrPartListService"
 * @spring.property name="maPmMstrPartListDAO" ref="maPmMstrPartListDAO"
 * @spring.property name="maPmMstrPartDetailService" ref="maPmMstrPartDetailService"
 */
public class MaPmMstrPartListServiceImpl implements MaPmMstrPartListService
{
    private MaPmMstrPartListDAO maPmMstrPartListDAO = null;
    private MaPmMstrPartDetailService maPmMstrPartDetailService = null;


	public MaPmMstrPartDetailService getMaPmMstrPartDetailService()
    {
        return maPmMstrPartDetailService;
    }

    public void setMaPmMstrPartDetailService(
            MaPmMstrPartDetailService maPmMstrPartDetailService)
    {
        this.maPmMstrPartDetailService = maPmMstrPartDetailService;
    }

    public List findPartList(MaPmMstrCommonDTO maPmMstrCommonDTO, User loginUser)
    {      
        return maPmMstrPartListDAO.findPartList(maPmMstrCommonDTO, loginUser);
    }

	public MaPmMstrPartListDAO getMaPmMstrPartListDAO() {
		return maPmMstrPartListDAO;
	}

	public void setMaPmMstrPartListDAO(MaPmMstrPartListDAO maPmMstrPartListDAO) {
		this.maPmMstrPartListDAO = maPmMstrPartListDAO;
	}
	
	public int deletePartList(String[] deleteRows, User user) throws Exception{
        int result = 0;

        if(!deleteRows.equals(null) && deleteRows.length>0) {
            List list = new ArrayList();
            MaPmMstrPartDetailDTO maPmMstrPartDetailDTO = new MaPmMstrPartDetailDTO();
            for(String id : deleteRows)
            {
                maPmMstrPartDetailDTO.setPmPartId(id);
                list.add(BeanUtils.cloneBean(maPmMstrPartDetailDTO));
            }
            result = maPmMstrPartListDAO.updateDeleteTag(list, user).length;
        }
        
        return result;
    }

	@Override
	public String findTotalCount(MaPmMstrCommonDTO maPmMstrCommonDTO, User user) throws Exception {
		return maPmMstrPartListDAO.findTotalCount(maPmMstrCommonDTO, user);
	}

    @Override
    public int insertPartList(MaPmMstrPartDetailDTO maPmMstrPartDetailDTO, MaPmMstrCommonDTO maPmMstrMstrCommonDTO, User user) throws Exception
    {
        int result = 0;
        
        String[] multiKey = maPmMstrPartDetailDTO.getMultiKey().split("\\+");
        
        for(int i=0; multiKey.length > i; i++)
        {
            maPmMstrPartDetailDTO.setPmPartId(maPmMstrPartListDAO.getNextSequence("SQAPM_PART_ID"));
            maPmMstrPartDetailDTO.setPartId(multiKey[i]);
            result = result + maPmMstrPartDetailService.insertDetail(maPmMstrPartDetailDTO, maPmMstrMstrCommonDTO);
        }
        
        return result;
    }
    
    @Override
    public int insertStdPartList(MaPmMstrPartDetailDTO maPmMstrPartDetailDTO, MaPmMstrCommonDTO maPmMstrMstrCommonDTO, User user) throws Exception
    {
    	int result = 0;
    	
    	String[] multiStdPartKey = maPmMstrPartDetailDTO.getMultiStdPartKey().split("\\+");
    	
    	for(int i=0; multiStdPartKey.length > i; i++)
    	{
    		maPmMstrPartDetailDTO.setPmPartId(maPmMstrPartListDAO.getNextSequence("SQAPM_PART_ID"));
    		maPmMstrPartDetailDTO.setPartId(multiStdPartKey[i]);
    		result = result + maPmMstrPartDetailService.insertDetail(maPmMstrPartDetailDTO, maPmMstrMstrCommonDTO);
    	}
    	
    	return result;
    }
}

