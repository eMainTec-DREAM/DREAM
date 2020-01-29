package dream.mgr.usrgrp.service.spring;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.util.CommonUtil;
import dream.mgr.usrgrp.dao.MgrUsrGrpPageAuthGridColDAO;
import dream.mgr.usrgrp.dto.MgrUsrGrpPageAuthGridColDTO;
import dream.mgr.usrgrp.service.MgrUsrGrpPageAuthGridColService;

/**
 * 화면권한설정상세탭버튼권한
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="mgrUsrGrpPageAuthGridColServiceTarget"
 * @spring.txbn id="mgrUsrGrpPageAuthGridColService"
 * @spring.property name="mgrUsrGrpPageAuthGridColDAO" ref="mgrUsrGrpPageAuthGridColDAO"
 */
public class MgrUsrGrpPageAuthGridColServiceImpl implements MgrUsrGrpPageAuthGridColService
{
	private MgrUsrGrpPageAuthGridColDAO mgrUsrGrpPageAuthGridColDAO = null;
	
	public MgrUsrGrpPageAuthGridColDAO getMgrUsrGrpPageAuthGridColDAO()
    {
        return mgrUsrGrpPageAuthGridColDAO;
    }

    public void setMgrUsrGrpPageAuthGridColDAO(MgrUsrGrpPageAuthGridColDAO mgrUsrGrpPageAuthGridColDAO)
    {
        this.mgrUsrGrpPageAuthGridColDAO = mgrUsrGrpPageAuthGridColDAO;
    }

    @Override
    public List findList(MgrUsrGrpPageAuthGridColDTO mgrUsrGrpPageAuthGridColDTO, User user) throws Exception
    {
        return mgrUsrGrpPageAuthGridColDAO.findList(mgrUsrGrpPageAuthGridColDTO, user);
    }
    
    @Override
    public MgrUsrGrpPageAuthGridColDTO findDetail(MgrUsrGrpPageAuthGridColDTO mgrUsrGrpPageAuthGridColDTO, User user) throws Exception
    {
        Map map = (Map) CommonUtil.makeJson(mgrUsrGrpPageAuthGridColDAO.findList(mgrUsrGrpPageAuthGridColDTO, user)).get(0);
        return (MgrUsrGrpPageAuthGridColDTO) CommonUtil.makeDTO(map, MgrUsrGrpPageAuthGridColDTO.class);
    }

    @Override
    public int updateDetail(MgrUsrGrpPageAuthGridColDTO mgrUsrGrpPageAuthGridColDTO, User user) throws Exception
    {
        int result = 0;
        String auId = mgrUsrGrpPageAuthGridColDTO.getUgpgridcolauId();
		if("".equals(auId) || auId.equals(null)) {
			mgrUsrGrpPageAuthGridColDTO.setUgpgridcolauId(mgrUsrGrpPageAuthGridColDAO.getNextSequence("SQAUGPGRIDCOLAU_ID"));
			result = mgrUsrGrpPageAuthGridColDAO.insertAuStatus(mgrUsrGrpPageAuthGridColDTO, user);
		} else 
			result = mgrUsrGrpPageAuthGridColDAO.updateAuStatus(mgrUsrGrpPageAuthGridColDTO, user);
		return result;
    }

    @Override
    public String findTotalCount(MgrUsrGrpPageAuthGridColDTO mgrUsrGrpPageAuthGridColDTO, User user) throws Exception
    {
        return mgrUsrGrpPageAuthGridColDAO.findTotalCount(mgrUsrGrpPageAuthGridColDTO, user);
    }

	@Override
	public int updAuthStatus(String[] pggridcolId, String[] ugpgridcolauId, String[] usrgrpId, String changeStatus, User user) throws Exception {
		 int result = 0;

		 if(!pggridcolId.equals(null)) {
            for(int i=0; i<pggridcolId.length; i++)
            {
            	result = result + this.updAuthStatus(pggridcolId[i], ugpgridcolauId[i], usrgrpId[i], changeStatus, user);
            }
        }
        return result;
	}

	private int updAuthStatus(String pggridcolId, String ugpgridcolauId, String usrgrpId, String changeStatus, User user) throws Exception {

		int result = 0;
		MgrUsrGrpPageAuthGridColDTO mgrUsrGrpPageAuthGridColDTO = new MgrUsrGrpPageAuthGridColDTO();
		mgrUsrGrpPageAuthGridColDTO.setPggridcolId(pggridcolId);
		mgrUsrGrpPageAuthGridColDTO.setUsrgrpId(usrgrpId);
		
		MgrUsrGrpPageAuthGridColDTO authPggridColDTO = this.findDetail(mgrUsrGrpPageAuthGridColDTO, user);
		String auId = authPggridColDTO.getUgpgridcolauId();
		
		mgrUsrGrpPageAuthGridColDTO.setUsrgrpId(authPggridColDTO.getUsrgrpId());
		mgrUsrGrpPageAuthGridColDTO.setPageId(authPggridColDTO.getPageId());
		mgrUsrGrpPageAuthGridColDTO.setGridId(authPggridColDTO.getGridId());
		mgrUsrGrpPageAuthGridColDTO.setColumnId(authPggridColDTO.getColumnId());
		mgrUsrGrpPageAuthGridColDTO.setAuthLimitTypeId(changeStatus);
		
		if("".equals(auId) || auId.equals(null)) {
			mgrUsrGrpPageAuthGridColDTO.setUgpgridcolauId(mgrUsrGrpPageAuthGridColDAO.getNextSequence("SQAUGPGRIDCOLAU_ID"));
			result = mgrUsrGrpPageAuthGridColDAO.insertAuStatus(mgrUsrGrpPageAuthGridColDTO, user);
		} else 
			result = mgrUsrGrpPageAuthGridColDAO.updateAuStatus(mgrUsrGrpPageAuthGridColDTO, user);

		return result;
	}

}

