package dream.scheduler.autoschedule.service;
/**
 * Batch ���� �������̽�
 * @author kim21017
 *
 */
public interface MaBatchService
{
	/**
	 * �����۾� ��ü������ �����ϴ� ���α׷�
	 * @author  kim21017
	 * @version $Id: MaBatchService.java,v 1.0 2016/03/24 08:20:09 kim21017 Exp $ 
	 * @since   1.0
	 * 
	 * @throws Exception
	 */
	public void SP_PM_MAKE_SCHEDULE_BYALL(String compNo, String userNo) throws Exception;
	/**
	 * �����۾� �������� �۾���(WO)�� �ڵ����� ������ �ִ� ���α׷�
	 * @author  kim21017
	 * @version $Id: MaBatchService.java,v 1.0 2016/03/24 08:20:09 kim21017 Exp $ 
	 * @since   1.0
	 * 
	 * @throws Exception
	 */
	public void SP_PM_MAKE_TO_ALLSCHED(String compNo, String userNo) throws Exception;
	/**
	 * �Ϻ��� �����Ȳ����͸� �����͸� �������ִ� ���α׷�
	 * @author  kim21017
	 * @version $Id: MaBatchService.java,v 1.0 2016/03/24 08:20:09 kim21017 Exp $ 
	 * @since   1.0
	 * 
	 * @throws Exception
	 */
	public void SP_MAKE_USE_MONITORING(String compNo, String userNo) throws Exception;
	/**
	 * default ������ǥ�� �������ִ� ���α׷�.
	 * @author  kim21017
	 * @version $Id: MaBatchService.java,v 1.0 2016/03/24 08:20:09 kim21017 Exp $ 
	 * @since   1.0
	 * 
	 * @throws Exception
	 */
	public void SP_PM_MAKE_TAMTPOINT(String compNo, String userNo) throws Exception;
	/**
	 * default ���ڱݾ��� �������ִ� ���α׷�.
	 * @author  kim21017
	 * @version $Id: MaBatchService.java,v 1.0 2016/03/24 08:20:09 kim21017 Exp $ 
	 * @since   1.0
	 * 
	 * @throws Exception
	 */
	public void SP_MAKE_TAINVESTAMT(String compNo, String userNo) throws Exception;
	/**
	 * default ���ΰ����ð� �������ִ� ���α׷�.
	 * @author  kim21017
	 * @version $Id: MaBatchService.java,v 1.0 2016/03/24 08:20:09 kim21017 Exp $ 
	 * @since   1.0
	 * 
	 * @throws Exception
	 */
	public void SP_MAKE_TALNWRKTIME(String compNo, String userNo) throws Exception;
	/**
	 * ���� ����ī���ٸ� default�� ������ִ� ���α׷�
	 * @author  kim21017
	 * @version $Id: MaBatchService.java,v 1.0 2016/03/24 08:20:09 kim21017 Exp $ 
	 * @since   1.0
	 * 
	 * @throws Exception
	 */
	public void SP_SETDEFAULT_DUMYDAYS(String compNo, String userNo) throws Exception;
	/**
	 * �Ϻ������� �����͸� �����ϴ� ���α׷�[���� 1�Ϻ��� ���ñ��� �����]
	 * @author  kim21017
	 * @version $Id: MaBatchService.java,v 1.0 2016/03/24 08:20:09 kim21017 Exp $ 
	 * @since   1.0
	 * 
	 * @throws Exception
	 */
	public void SP_KPI_MAKE_TAKPIDLOCDN(String compNo, String userNo) throws Exception;
	/**
	 * ���������� �����͸� �����ϴ� ���α׷�[����,��� �����]
	 * @author  kim21017
	 * @version $Id: MaBatchService.java,v 1.0 2016/03/24 08:20:09 kim21017 Exp $ 
	 * @since   1.0
	 * 
	 * @throws Exception
	 */
	public void SP_KPI_MAKE_TAKPIMLOCDN(String compNo, String userNo) throws Exception;
	/**
	 * ���������������� �����͸� �����ϴ� ���α׷�[���� 1�Ϻ��� ���ñ��� �����]
	 * @author  kim21017
	 * @version $Id: MaBatchService.java,v 1.0 2016/03/24 08:20:09 kim21017 Exp $ 
	 * @since   1.0
	 * 
	 * @throws Exception
	 */
	public void SP_KPI_MAKE_TAKPIDLOCCTGDN(String compNo, String userNo) throws Exception;
	/**
	 * ���������� ������ �����͸� ������ ����
	 * @author  kim21017
	 * @version $Id: MaBatchService.java,v 1.0 2016/03/24 08:20:09 kim21017 Exp $ 
	 * @since   1.0
	 * 
	 * @throws Exception
	 */
	public void SP_KPI_MAKE_TAKPIMLOCCTGDN(String compNo, String userNo) throws Exception;
	/**
	 * ���������׸� ��ǥ �����͸� �����ϴ� ���α׷�.
	 * @author  kim21017
	 * @version $Id: MaBatchService.java,v 1.0 2016/03/24 08:20:09 kim21017 Exp $ 
	 * @since   1.0
	 * 
	 * @throws Exception
	 */
	public void SP_KPI_MAKE_TAKPIMMPOINT(String compNo, String userNo) throws Exception;
	/**
	 * �ְ����� ������ǥ �����͸� �����ϴ� ���α׷�
	 * @author  kim21017
	 * @version $Id: MaBatchService.java,v 1.0 2016/03/24 08:20:09 kim21017 Exp $ 
	 * @since   1.0
	 * 
	 * @throws Exception
	 */
	public void SP_KPI_MAKE_TAKPIWMPOINT(String compNo, String userNo) throws Exception;
	/**
	 * ����������ȹ ��ǥ �����͸� �����ϴ� ���α׷�
	 * @author  kim21017
	 * @version $Id: MaBatchService.java,v 1.0 2016/03/24 08:20:09 kim21017 Exp $ 
	 * @since   1.0
	 * 
	 * @throws Exception
	 */
	public void SP_KPI_MAKE_TAKPIMEDU(String compNo, String userNo) throws Exception;
	/**
	 * ���� ���Һθ� �����ϴ� ���α׷�
	 * @author  kim21017
	 * @version $Id: MaBatchService.java,v 1.0 2016/03/24 08:20:09 kim21017 Exp $ 
	 * @since   1.0
	 * 
	 * @throws Exception
	 */
	public void SP_KPI_MAKE_TAPTMONTHLYSTOCK(String compNo, String userNo) throws Exception;
	
	
	public void SP_WOMAKE_4WP_BYALL(String compNo, String userNo) throws Exception;
	/**
	 * ���庰 ���ν��� �������̽�
	 * @throws Exception
	 */
	public void COMP_INTERFACE(String compNo, String userNo) throws Exception;
	/**
	 * CP �������̽�
	 * @throws Exception
	 */
	public void SP_IF_UPD_CTCTR(String compNo, String userNo) throws Exception;
	/**
	 * �μ� �������̽�
	 * @throws Exception
	 */
	public void SP_IF_UPD_DEPT(String compNo, String userNo) throws Exception;
	/**
	 * ��� �������̽�
	 * @throws Exception
	 */
	public void SP_IF_UPD_EMP(String compNo, String userNo) throws Exception;
	/**
	 * �ŷ�ó �������̽�
	 * @throws Exception
	 */
	public void SP_IF_UPD_VENDOR(String compNo, String userNo) throws Exception;
	
	/**
	 * ���ϼ��� ����
	 * @throws Exception
	 */
	public void sendMailMessageList(String compNo, String userNo) throws Exception;
}
