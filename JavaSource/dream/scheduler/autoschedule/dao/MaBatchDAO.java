package dream.scheduler.autoschedule.dao;

import java.io.IOException;
import java.util.List;
import java.util.Map;



/**
 * Batch DAO
 * @author  kim21017
 * @version $Id: MaBatchDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 */
public interface MaBatchDAO
{
    public void SP_PM_MAKE_SCHEDULE_BYALL(String compNo, String userNo) throws Exception;
    public void SP_PM_MAKE_TO_ALLSCHED(String compNo, String userNo) throws Exception;
    public void SEND_WORATE_EMAIL(String compNo, String userNo, String email, String contents) throws Exception;
    public void SP_MAKE_USE_MONITORING(String compNo, String userNo) throws Exception;
    public void SP_PM_MAKE_TAMTPOINT(String compNo, String userNo) throws Exception;
    public void SP_MAKE_TAINVESTAMT(String compNo, String userNo) throws Exception;
    public void SP_MAKE_TALNWRKTIME(String compNo, String userNo) throws Exception;
    public void SP_SETDEFAULT_DUMYDAYS(String compNo, String userNo) throws Exception;
    public void SP_KPI_MAKE_TAKPIDLOCDN(String compNo, String userNo) throws Exception;
    public void SP_KPI_MAKE_TAKPIMLOCDN(String compNo, String userNo) throws Exception;
    public void SP_KPI_MAKE_TAKPIDLOCCTGDN(String compNo, String userNo) throws Exception;
    public void SP_KPI_MAKE_TAKPIMLOCCTGDN(String compNo, String userNo) throws Exception;
    public void SP_KPI_MAKE_TAKPIMMPOINT(String compNo, String userNo) throws Exception;
    public void SP_KPI_MAKE_TAKPIWMPOINT(String compNo, String userNo) throws Exception;
    public void SP_KPI_MAKE_TAKPIMEDU(String compNo, String userNo) throws Exception;
    public void SP_KPI_MAKE_TAPTMONTHLYSTOCK(String compNo, String userNo) throws Exception;
	
	public String[] getIfCompNo() throws Exception;
	public String getIfUserNo() throws Exception;
	public String getIfMailAddress() throws Exception;
	public String[][] getMailContents(String compNo) throws Exception;
    /**
     * ERP 자재 싱크 프로시져
     * @author  jung7126
     * @version $Id:$
     * @since   1.0
     * 
     * @param compNo
     * @param userNo
     */
    public void SP_IF_UPD_TAPARTS(String compNo, String userNo);
    
    public List getErpCode(String compNo);
    
    public void SP_IF_UPD_TAPTSTOCK(String compNo, String userNo);
    public void SP_IF_UPD_TAERPISSHIST(String compNo, String userNo);
    public void SP_IF_UPD_TXERPPRPOLIST(String compNo, String userNo);
    
    public void SP_IF_UPD_TXLNWRKTIME(String compNo, String userNo);
    
    public void SP_IF_UPD_TXLNNTWRKTIME(String compNo, String userNo);
    
    public List findGlobalApi(String compNo);
    
    public void insertGlobalApi(Map map) throws IOException;
    
	/**
	 * Global API Parts Stock Info
	 * @author  Mark
	 * @version $Id: codetemplates.xml,v 1.1 2007/02/15 06:41:03 dawn Exp $
	 * @since   1.0
	 * 
	 * @param compNo
	 * @return
	 */
	public List<Map> findGlobalApiParts(String compNo);

    public String insertGlobalPartApi() throws IOException;
    
    public void executeBatch(String queryStr, final List<Object[]> paramList) throws IOException;
    
	public void SP_WOMAKE_4WP_BYALL(String string);

    public void DAEWOONG_IF_POP_EQ(String compNo, String userNo) throws Exception;
    public void DAEWOONG_IF_POP_EQACT(String compNo, String userNo) throws Exception;
    public void DAEWOONG_IF_UPD_PT_SAFTY_STOCK(String compNo, String userNo) throws Exception;
    public void DAEWOONG_IF_UPD_USER(String compNo, String userNo) throws Exception;
    public List NULL_PASSWORD_USER(String compNo) throws Exception;
    public void SET_PASSWORD(String compNo, String userNo) throws Exception;
    
    //유저 인터페이스 
    public void SP_IF_UPD_EMP(String compNo, String userNo) throws Exception;
    //거래처 인터페이스 
    public void SP_IF_UPD_VENDOR(String compNo, String userNo) throws Exception;
    //CP 인터페이스 
    public void SP_IF_UPD_CTCTR(String compNo, String userNo) throws Exception;
    //부서 인터페이스 
    public void SP_IF_UPD_DEPT(String compNo, String userNo) throws Exception;
    
    //메일보낼 리스트 얻어오기
    public List findMailMessageList(String compNo) throws Exception;
    //메일보내기 결과 업데이트
    public int updateMailMessageList(String id, int code, String failMsg);
    //메일보내기 status 업데이트
    public int updateMailMessageRunning(String id);
    
    /**
     * Find Mail Attachments
     * @author  jung7126
     * @version $Id:$
     * @param objectType 
     * @param compNo 
     * @since   1.0
     * 
     * @param valueOf
     * @return
     */
    public List getMessageAttachment(String messageListId, String compNo, String objectType);
}