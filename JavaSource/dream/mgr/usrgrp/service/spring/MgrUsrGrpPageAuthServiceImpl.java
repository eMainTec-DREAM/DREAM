package dream.mgr.usrgrp.service.spring;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import common.bean.User;
import common.util.CommonUtil;
import dream.mgr.usrgrp.dao.MgrUsrGrpPageAuthDAO;
import dream.mgr.usrgrp.dto.MgrUsrGrpPageAuthDTO;
import dream.mgr.usrgrp.service.MgrUsrGrpPageAuthService;

/**
 * 화면권한설정 
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="mgrUsrGrpPageAuthServiceTarget"
 * @spring.txbn id="mgrUsrGrpPageAuthService"
 * @spring.property name="mgrUsrGrpPageAuthDAO" ref="mgrUsrGrpPageAuthDAO"
 */
public class MgrUsrGrpPageAuthServiceImpl implements MgrUsrGrpPageAuthService
{
	private MgrUsrGrpPageAuthDAO mgrUsrGrpPageAuthDAO = null;
	
	public MgrUsrGrpPageAuthDAO getMgrUsrGrpPageAuthDAO()
    {
        return mgrUsrGrpPageAuthDAO;
    }

    public void setMgrUsrGrpPageAuthDAO(MgrUsrGrpPageAuthDAO mgrUsrGrpPageAuthDAO)
    {
        this.mgrUsrGrpPageAuthDAO = mgrUsrGrpPageAuthDAO;
    }

    @Override
    public List findList(MgrUsrGrpPageAuthDTO mgrUsrGrpPageAuthDTO, User user) throws Exception
    {
        return mgrUsrGrpPageAuthDAO.findList(mgrUsrGrpPageAuthDTO, user);
    }
    
    @Override
    public MgrUsrGrpPageAuthDTO findDetail(MgrUsrGrpPageAuthDTO mgrUsrGrpPageAuthDTO, User user) throws Exception
    {
        return (MgrUsrGrpPageAuthDTO)CommonUtil.makeDetailFromList(mgrUsrGrpPageAuthDAO.findList(mgrUsrGrpPageAuthDTO, user), mgrUsrGrpPageAuthDTO);
    }

    @Override
    public int updateDetail(MgrUsrGrpPageAuthDTO mgrUsrGrpPageAuthDTO, User user) throws Exception
    {
        int result = 0;
        
        if("Y".equals(mgrUsrGrpPageAuthDTO.getIsAuth())) {
            result = this.inputAuth(mgrUsrGrpPageAuthDTO, user);
        }
        else if("N".equals(mgrUsrGrpPageAuthDTO.getIsAuth())) {
            result = mgrUsrGrpPageAuthDAO.deleteList(mgrUsrGrpPageAuthDTO.getPageId(), mgrUsrGrpPageAuthDTO.getUsrgrpId(), user);
        }
        
        return result;
    }

    @Override
    public int[] inputAuth(String[] pageIds, String[] usrgrpIds, User user) throws Exception
    {
        int[] result = null;

        MgrUsrGrpPageAuthDTO mgrUsrGrpPageAuthDTO = new MgrUsrGrpPageAuthDTO();
        List list = new ArrayList();
        if(!pageIds.equals(null)) {
            for(int i=0; i<pageIds.length; i++)
            {
                mgrUsrGrpPageAuthDTO.setPageId(pageIds[i]);
                mgrUsrGrpPageAuthDTO.setUsrgrpId(usrgrpIds[i]);
                
                String ugpgauId = this.findDetail(mgrUsrGrpPageAuthDTO, user).getUgpgauId();
                if("".equals(ugpgauId) || "null".equals(ugpgauId))
                {
                    mgrUsrGrpPageAuthDTO.setUgpgauId(mgrUsrGrpPageAuthDAO.getNextSequence("SQAUGPGAU_ID"));
                    list.add(BeanUtils.cloneBean(mgrUsrGrpPageAuthDTO));
                }
            }
            
            return this.inputAuth(list, user);
        }
        
        return result;
    }
    
    private int[] inputAuth(List<MgrUsrGrpPageAuthDTO> list, User user) throws Exception
    {
        return mgrUsrGrpPageAuthDAO.insertDetail(list, user);
    }
    
    private int inputAuth(MgrUsrGrpPageAuthDTO mgrUsrGrpPageAuthDTO, User user) throws Exception
    {
        
        String ugpgauId = mgrUsrGrpPageAuthDTO.getUgpgauId();
        if("".equals(ugpgauId) || "null".equals(ugpgauId)) mgrUsrGrpPageAuthDTO.setUgpgauId(mgrUsrGrpPageAuthDAO.getNextSequence("SQAUGPGAU_ID"));
        
        List<MgrUsrGrpPageAuthDTO> list = new ArrayList<MgrUsrGrpPageAuthDTO>();
        list.add(mgrUsrGrpPageAuthDTO);
        
        int[] rtn =  mgrUsrGrpPageAuthDAO.insertDetail(list, user);
        
        return rtn[0];
    }
    
    @Override
    public int deleteAuth(String[] pageIds, String[] usrgrpIds, User user) throws Exception
    {
        int result = 0;
        
        if(!pageIds.equals(null)) {
            for(int i=0; i<pageIds.length; i++)
            {
                result = result + mgrUsrGrpPageAuthDAO.deleteList(pageIds[i], usrgrpIds[i], user);
            }
        }
        
        return result;
    }

    @Override
    public String findTotalCount(MgrUsrGrpPageAuthDTO mgrUsrGrpPageAuthDTO, User user) throws Exception
    {
        return mgrUsrGrpPageAuthDAO.findTotalCount(mgrUsrGrpPageAuthDTO, user);
    }

}

