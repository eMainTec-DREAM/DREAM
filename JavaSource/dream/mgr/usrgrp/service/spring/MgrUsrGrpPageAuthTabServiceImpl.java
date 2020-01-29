package dream.mgr.usrgrp.service.spring;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.util.CommonUtil;
import dream.mgr.usrgrp.dao.MgrUsrGrpPageAuthTabDAO;
import dream.mgr.usrgrp.dto.MgrUsrGrpPageAuthTabDTO;
import dream.mgr.usrgrp.service.MgrUsrGrpPageAuthTabService;

/**
 * 화면권한설정상세탭탭권한
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="mgrUsrGrpPageAuthTabServiceTarget"
 * @spring.txbn id="mgrUsrGrpPageAuthTabService"
 * @spring.property name="mgrUsrGrpPageAuthTabDAO" ref="mgrUsrGrpPageAuthTabDAO"
 */
public class MgrUsrGrpPageAuthTabServiceImpl implements MgrUsrGrpPageAuthTabService
{
	private MgrUsrGrpPageAuthTabDAO mgrUsrGrpPageAuthTabDAO = null;
	
	public MgrUsrGrpPageAuthTabDAO getMgrUsrGrpPageAuthTabDAO()
    {
        return mgrUsrGrpPageAuthTabDAO;
    }

    public void setMgrUsrGrpPageAuthTabDAO(MgrUsrGrpPageAuthTabDAO mgrUsrGrpPageAuthTabDAO)
    {
        this.mgrUsrGrpPageAuthTabDAO = mgrUsrGrpPageAuthTabDAO;
    }

    @Override
    public List findList(MgrUsrGrpPageAuthTabDTO mgrUsrGrpPageAuthTabDTO, User user) throws Exception
    {
        return mgrUsrGrpPageAuthTabDAO.findList(mgrUsrGrpPageAuthTabDTO, user);
    }
    
    @Override
    public MgrUsrGrpPageAuthTabDTO findDetail(MgrUsrGrpPageAuthTabDTO mgrUsrGrpPageAuthTabDTO, User user) throws Exception
    {
        Map map = (Map) CommonUtil.makeJson(mgrUsrGrpPageAuthTabDAO.findList(mgrUsrGrpPageAuthTabDTO, user)).get(0);
        return (MgrUsrGrpPageAuthTabDTO) CommonUtil.makeDTO(map, MgrUsrGrpPageAuthTabDTO.class);
    }

    @Override
    public int updateDetail(MgrUsrGrpPageAuthTabDTO mgrUsrGrpPageAuthTabDTO, User user) throws Exception
    {
        int result = 0;
        
        if("Y".equals(mgrUsrGrpPageAuthTabDTO.getIsAuth())) {
            result = this.inputAuth(mgrUsrGrpPageAuthTabDTO.getPgpageId(), mgrUsrGrpPageAuthTabDTO.getUsrgrpId(), user);
        }
        else if("N".equals(mgrUsrGrpPageAuthTabDTO.getIsAuth())) {
            result = mgrUsrGrpPageAuthTabDAO.deleteList(mgrUsrGrpPageAuthTabDTO.getPgpageId(), mgrUsrGrpPageAuthTabDTO.getUsrgrpId(), user);
        }
        
        return result;
    }

    @Override
    public int inputAuth(String[] pgpageIds, String[] usrgrpIds, User user) throws Exception
    {
        int result = 0;

        if(!pgpageIds.equals(null)) {
            for(int i=0; i<pgpageIds.length; i++)
            {
                result = result + this.inputAuth(pgpageIds[i], usrgrpIds[i], user);
            }
        }
        
        return result;
    }
    
    private int inputAuth(String pgpageId, String usrgrpId, User user) throws Exception
    {
        int result = 0;
        
        MgrUsrGrpPageAuthTabDTO mgrUsrGrpPageAuthTabDTO = new MgrUsrGrpPageAuthTabDTO();
        mgrUsrGrpPageAuthTabDTO.setPgpageId(pgpageId);
        mgrUsrGrpPageAuthTabDTO.setUsrgrpId(usrgrpId);
        
        if("".equals(this.findDetail(mgrUsrGrpPageAuthTabDTO, user).getUgpgpgauId())) {
            mgrUsrGrpPageAuthTabDTO.setUgpgpgauId(mgrUsrGrpPageAuthTabDAO.getNextSequence("SQAUGPGPGAU_ID"));
            result = mgrUsrGrpPageAuthTabDAO.insertDetail(mgrUsrGrpPageAuthTabDTO, user);
        }
        
        return result;
    }
    
    @Override
    public int deleteAuth(String[] pgpageIds, String[] usrgrpIds, User user) throws Exception
    {
        int result = 0;
        
        if(!pgpageIds.equals(null)) {
            for(int i=0; i<pgpageIds.length; i++)
            {
                result = result + mgrUsrGrpPageAuthTabDAO.deleteList(pgpageIds[i], usrgrpIds[i], user);
            }
        }
        
        return result;
    }

    @Override
    public String findTotalCount(MgrUsrGrpPageAuthTabDTO mgrUsrGrpPageAuthTabDTO, User user) throws Exception
    {
        return mgrUsrGrpPageAuthTabDAO.findTotalCount(mgrUsrGrpPageAuthTabDTO, user);
    }

}

