package enhance.dream.scheduler.autoschedule.service;
/**
 * Dream회사 전용 서비스 인터페이스
 * 
 * 테스트 버전으로 업로드해놨고
 * 각 사이트에 맞게 패키지 구분하여 구성해야합니다.
 * 
 * 
 * @author kim21017
 *
 */
public interface DreamBatchService
{
	public void sendSmsMessageList() throws Exception;

    public void getImageFromUrl() throws Exception;
}
