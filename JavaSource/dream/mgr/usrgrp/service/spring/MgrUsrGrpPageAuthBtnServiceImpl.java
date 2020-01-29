package dream.mgr.usrgrp.service.spring;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.util.CommonUtil;
import dream.mgr.usrgrp.dao.MgrUsrGrpPageAuthBtnDAO;
import dream.mgr.usrgrp.dto.MgrUsrGrpPageAuthBtnDTO;
import dream.mgr.usrgrp.service.MgrUsrGrpPageAuthBtnService;

/**
 * 화면권한설정상세탭버튼권한
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="mgrUsrGrpPageAuthBtnServiceTarget"
 * @spring.txbn id="mgrUsrGrpPageAuthBtnService"
 * @spring.property name="mgrUsrGrpPageAuthBtnDAO" ref="mgrUsrGrpPageAuthBtnDAO"
 */
public class MgrUsrGrpPageAuthBtnServiceImpl implements MgrUsrGrpPageAuthBtnService
{
	private MgrUsrGrpPageAuthBtnDAO mgrUsrGrpPageAuthBtnDAO = null;
	
	public MgrUsrGrpPageAuthBtnDAO getMgrUsrGrpPageAuthBtnDAO()
    {
        return mgrUsrGrpPageAuthBtnDAO;
    }

    public void setMgrUsrGrpPageAuthBtnDAO(MgrUsrGrpPageAuthBtnDAO mgrUsrGrpPageAuthBtnDAO)
    {
        this.mgrUsrGrpPageAuthBtnDAO = mgrUsrGrpPageAuthBtnDAO;
    }

    @Override
    public List findList(MgrUsrGrpPageAuthBtnDTO mgrUsrGrpPageAuthBtnDTO, User user) throws Exception
    {
        return mgrUsrGrpPageAuthBtnDAO.findList(mgrUsrGrpPageAuthBtnDTO, user);
    }
    
    @Override
    public MgrUsrGrpPageAuthBtnDTO findDetail(MgrUsrGrpPageAuthBtnDTO mgrUsrGrpPageAuthBtnDTO, User user) throws Exception
    {
        Map map = (Map) CommonUtil.makeJson(mgrUsrGrpPageAuthBtnDAO.findList(mgrUsrGrpPageAuthBtnDTO, user)).get(0);
        return (MgrUsrGrpPageAuthBtnDTO) CommonUtil.makeDTO(map, MgrUsrGrpPageAuthBtnDTO.class);
    }

    @Override
    public int updateDetail(MgrUsrGrpPageAuthBtnDTO mgrUsrGrpPageAuthBtnDTO, User user) throws Exception
    {
        int result = 0;
        
        if("Y".equals(mgrUsrGrpPageAuthBtnDTO.getIsAuth())) {
            result = this.inputAuth(mgrUsrGrpPageAuthBtnDTO.getPgbtnId(), mgrUsrGrpPageAuthBtnDTO.getUsrgrpId(), user);
        }
        else if("N".equals(mgrUsrGrpPageAuthBtnDTO.getIsAuth())) {
            result = mgrUsrGrpPageAuthBtnDAO.deleteList(mgrUsrGrpPageAuthBtnDTO.getPgbtnId(), mgrUsrGrpPageAuthBtnDTO.getUsrgrpId(), user);
        }
        
        return result;
    }

    @Override
    public int inputAuth(String[] pgbtnIds, String[] usrgrpIds, User user) throws Exception
    {
        int result = 0;

        if(!pgbtnIds.equals(null)) {
            for(int i=0; i<pgbtnIds.length; i++)
            {
                result = result + this.inputAuth(pgbtnIds[i], usrgrpIds[i], user);
            }
        }
        
        return result;
    }
    
    private int inputAuth(String pgbtnId, String usrgrpId, User user) throws Exception
    {
        int result = 0;
        
        MgrUsrGrpPageAuthBtnDTO mgrUsrGrpPageAuthBtnDTO = new MgrUsrGrpPageAuthBtnDTO();
        mgrUsrGrpPageAuthBtnDTO.setPgbtnId(pgbtnId);
        mgrUsrGrpPageAuthBtnDTO.setUsrgrpId(usrgrpId);
        
        if("".equals(this.findDetail(mgrUsrGrpPageAuthBtnDTO, user).getUgpgbtnauId())) {
            mgrUsrGrpPageAuthBtnDTO.setUgpgbtnauId(mgrUsrGrpPageAuthBtnDAO.getNextSequence("SQAUGPGBTN_ID"));
            result = mgrUsrGrpPageAuthBtnDAO.insertDetail(mgrUsrGrpPageAuthBtnDTO, user);
        }
        
        return result;
    }
    
    @Override
    public int deleteAuth(String[] pgbtnIds, String[] usrgrpIds, User user) throws Exception
    {
        int result = 0;
        
        if(!pgbtnIds.equals(null)) {
            for(int i=0; i<pgbtnIds.length; i++)
            {
                result = result + mgrUsrGrpPageAuthBtnDAO.deleteList(pgbtnIds[i], usrgrpIds[i], user);
            }
        }
        
        return result;
    }

    @Override
    public String findTotalCount(MgrUsrGrpPageAuthBtnDTO mgrUsrGrpPageAuthBtnDTO, User user) throws Exception
    {
        return mgrUsrGrpPageAuthBtnDAO.findTotalCount(mgrUsrGrpPageAuthBtnDTO, user);
    }

}

