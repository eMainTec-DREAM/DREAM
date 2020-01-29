package dream.scheduler.autoschedule.service;
/**
 * Batch 서비스 인터페이스
 * @author kim21017
 *
 */
public interface SchAutoSchCommonService
{
	/**
	 * 예방작업 전체일정을 생성하는 프로그램
	 * @author  kim21017
	 * @version $Id: MaBatchService.java,v 1.0 2016/03/24 08:20:09 kim21017 Exp $ 
	 * @since   1.0
	 * 
	 * @throws Exception
	 */
	void SP_PM_MAKE_SCHEDULE_BYALL() throws Exception;
	/**
	 * 예방작업 일정에서 작업서(WO)를 자동으로 발행해 주는 프로그램
	 * @author  kim21017
	 * @version $Id: MaBatchService.java,v 1.0 2016/03/24 08:20:09 kim21017 Exp $ 
	 * @since   1.0
	 * 
	 * @throws Exception
	 */
	void SP_PM_MAKE_TO_ALLSCHED() throws Exception;
	/**
	 * 일별로 사용현황모니터링 데이터를 집계해주는 프로그램
	 * @author  kim21017
	 * @version $Id: MaBatchService.java,v 1.0 2016/03/24 08:20:09 kim21017 Exp $ 
	 * @since   1.0
	 * 
	 * @throws Exception
	 */
	void SP_MAKE_USE_MONITORING() throws Exception;
	/**
	 * default 보전목표를 셋팅해주는 프로그램.
	 * @author  kim21017
	 * @version $Id: MaBatchService.java,v 1.0 2016/03/24 08:20:09 kim21017 Exp $ 
	 * @since   1.0
	 * 
	 * @throws Exception
	 */
	void SP_PM_MAKE_TAMTPOINT() throws Exception;
	/**
	 * default 투자금액을 셋팅해주는 프로그램.
	 * @author  kim21017
	 * @version $Id: MaBatchService.java,v 1.0 2016/03/24 08:20:09 kim21017 Exp $ 
	 * @since   1.0
	 * 
	 * @throws Exception
	 */
	void SP_MAKE_TAINVESTAMT() throws Exception;
	/**
	 * default 라인가동시간 셋팅해주는 프로그램.
	 * @author  kim21017
	 * @version $Id: MaBatchService.java,v 1.0 2016/03/24 08:20:09 kim21017 Exp $ 
	 * @since   1.0
	 * 
	 * @throws Exception
	 */
	void SP_MAKE_TALNWRKTIME() throws Exception;
	/**
	 * 공장 가동카렌다를 default로 만들어주는 프로그램
	 * @author  kim21017
	 * @version $Id: MaBatchService.java,v 1.0 2016/03/24 08:20:09 kim21017 Exp $ 
	 * @since   1.0
	 * 
	 * @throws Exception
	 */
	void SP_SETDEFAULT_DUMYDAYS() throws Exception;
	/**
	 * 일별고장율 데이터를 집계하는 프로그램[전월 1일부터 오늘까지 재생성]
	 * @author  kim21017
	 * @version $Id: MaBatchService.java,v 1.0 2016/03/24 08:20:09 kim21017 Exp $ 
	 * @since   1.0
	 * 
	 * @throws Exception
	 */
	void SP_KPI_MAKE_TAKPIDLOCDN() throws Exception;
	/**
	 * 월별고장율 데이터를 집계하는 프로그램[전월,당월 재생성]
	 * @author  kim21017
	 * @version $Id: MaBatchService.java,v 1.0 2016/03/24 08:20:09 kim21017 Exp $ 
	 * @since   1.0
	 * 
	 * @throws Exception
	 */
	void SP_KPI_MAKE_TAKPIMLOCDN() throws Exception;
	/**
	 * 설비종류별고장율 데이터를 집계하는 프로그램[전월 1일부터 오늘까지 재생성]
	 * @author  kim21017
	 * @version $Id: MaBatchService.java,v 1.0 2016/03/24 08:20:09 kim21017 Exp $ 
	 * @since   1.0
	 * 
	 * @throws Exception
	 */
	void SP_KPI_MAKE_TAKPIDLOCCTGDN() throws Exception;
	/**
	 * 설비종류별 고장율 데이터를 월별로 집계
	 * @author  kim21017
	 * @version $Id: MaBatchService.java,v 1.0 2016/03/24 08:20:09 kim21017 Exp $ 
	 * @since   1.0
	 * 
	 * @throws Exception
	 */
	void SP_KPI_MAKE_TAKPIMLOCCTGDN() throws Exception;
	/**
	 * 월간보전항목 지표 데이터를 생성하는 프로그램.
	 * @author  kim21017
	 * @version $Id: MaBatchService.java,v 1.0 2016/03/24 08:20:09 kim21017 Exp $ 
	 * @since   1.0
	 * 
	 * @throws Exception
	 */
	void SP_KPI_MAKE_TAKPIMMPOINT() throws Exception;
	/**
	 * 주간단위 고장지표 데이터를 생성하는 프로그램
	 * @author  kim21017
	 * @version $Id: MaBatchService.java,v 1.0 2016/03/24 08:20:09 kim21017 Exp $ 
	 * @since   1.0
	 * 
	 * @throws Exception
	 */
	void SP_KPI_MAKE_TAKPIWMPOINT() throws Exception;
	/**
	 * 월간교육계획 지표 데이터를 생성하는 프로그램
	 * @author  kim21017
	 * @version $Id: MaBatchService.java,v 1.0 2016/03/24 08:20:09 kim21017 Exp $ 
	 * @since   1.0
	 * 
	 * @throws Exception
	 */
	void SP_KPI_MAKE_TAKPIMEDU() throws Exception;
	/**
	 * 월간 수불부를 생성하는 프로그램
	 * @author  kim21017
	 * @version $Id: MaBatchService.java,v 1.0 2016/03/24 08:20:09 kim21017 Exp $ 
	 * @since   1.0
	 * 
	 * @throws Exception
	 */
	void SP_KPI_MAKE_TAPTMONTHLYSTOCK() throws Exception;
	
	
	void SP_WOMAKE_4WP_BYALL() throws Exception;
	/**
	 * 공장별 프로시저 인터페이스
	 * @throws Exception
	 */
	void COMP_INTERFACE() throws Exception;
	/**
	 * CP 인터페이스
	 * @throws Exception
	 */
	void SP_IF_UPD_CTCTR() throws Exception;
	/**
	 * 부서 인터페이스
	 * @throws Exception
	 */
	void SP_IF_UPD_DEPT() throws Exception;
	/**
	 * 사원 인터페이스
	 * @throws Exception
	 */
	void SP_IF_UPD_EMP() throws Exception;
	/**
	 * 거래처 인터페이스
	 * @throws Exception
	 */
	void SP_IF_UPD_VENDOR() throws Exception;
	
	/**
	 * 메일서비스 실행
	 * @throws Exception
	 */
	public void sendMailMessageList() throws Exception;
}
