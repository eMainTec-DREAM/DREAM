package dream.work.close.check.service.spring;

import common.bean.User;
import dream.work.close.check.dao.MgrWorkCloseCheckDetailDAO;
import dream.work.close.check.dto.MgrWorkCloseCheckCommonDTO;
import dream.work.close.check.dto.MgrWorkCloseCheckDetailDTO;
import dream.work.close.check.service.MgrWorkCloseCheckDetailService;

/**
 * MgrWorkCloseCheck Page - Detail Service implements
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * @spring.bean id="mgrWorkCloseCheckDetailServiceTarget"
 * @spring.txbn id="mgrWorkCloseCheckDetailService"
 * @spring.property name="mgrWorkCloseCheckDetailDAO" ref="mgrWorkCloseCheckDetailDAO"
 */
public class MgrWorkCloseCheckDetailServiceImpl implements MgrWorkCloseCheckDetailService
{
    private MgrWorkCloseCheckDetailDAO mgrWorkCloseCheckDetailDAO = null;
    
    public MgrWorkCloseCheckDetailDTO findDetail(MgrWorkCloseCheckCommonDTO mgrWorkCloseCheckCommonDTO, User user) throws Exception
    {
        return mgrWorkCloseCheckDetailDAO.findDetail(mgrWorkCloseCheckCommonDTO, user);
    }
    
    public int insertDetail(MgrWorkCloseCheckDetailDTO mgrWorkCloseCheckDetailDTO, User user) throws Exception
    {
    	mgrWorkCloseCheckDetailDTO.setStwrkWorkId(mgrWorkCloseCheckDetailDAO.getNextSequence("SQASTWRKWORK_ID"));
    	
    	mgrWorkCloseCheckDetailDAO.insertDetail(mgrWorkCloseCheckDetailDTO, user);
    	return mgrWorkCloseCheckDetailDAO.insertWorkDetail(mgrWorkCloseCheckDetailDTO, user);
    }
    
    public int updateDetail(MgrWorkCloseCheckDetailDTO mgrWorkCloseCheckDetailDTO, User user) throws Exception
    {
    	mgrWorkCloseCheckDetailDAO.updateDetail(mgrWorkCloseCheckDetailDTO, user);
    	return mgrWorkCloseCheckDetailDAO.updateWorkDetail(mgrWorkCloseCheckDetailDTO, user);
    }
    
    public int confirmDetail(MgrWorkCloseCheckDetailDTO mgrWorkCloseCheckDetailDTO, User user) throws Exception
    {
    	return mgrWorkCloseCheckDetailDAO.confirmDetail(mgrWorkCloseCheckDetailDTO, user);
    }
    
    public MgrWorkCloseCheckDetailDAO getMgrWorkCloseCheckDetailDAO() {
        return mgrWorkCloseCheckDetailDAO;
    }

    public void setMgrWorkCloseCheckDetailDAO(MgrWorkCloseCheckDetailDAO mgrWorkCloseCheckDetailDAO) {
        this.mgrWorkCloseCheckDetailDAO = mgrWorkCloseCheckDetailDAO;
    }

	@Override
	public int insertRevisionHistAndUpdateMstr(MgrWorkCloseCheckDetailDTO mgrWorkCloseCheckDetailDTO, User user) {
		String histId = mgrWorkCloseCheckDetailDAO.getNextSequence("SQAREVISIONHIST_ID");
		int result = 0;
		result+= mgrWorkCloseCheckDetailDAO.insertRevisionHist(mgrWorkCloseCheckDetailDTO, user, histId);
		result+= mgrWorkCloseCheckDetailDAO.updateStdPointMstrLastVersion(mgrWorkCloseCheckDetailDTO, user, histId);
		
		return result;
	}
}
