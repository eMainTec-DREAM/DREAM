package dream.mgr.exldata.service.spring;

import dream.mgr.exldata.service.MgrExldataUploadDetailService;

/**
 * ¿¢¼¿¾÷·Îµå
 * @author  js.lee
 * @version $Id:$
 * @since   1.0
 */
public abstract class AbstractMgrExldataUploadDetailService implements MgrExldataUploadDetailService
{    
	protected MgrExldataUploadDetailService mgrExldataUploadDetailService;
	
    public AbstractMgrExldataUploadDetailService(MgrExldataUploadDetailService mgrExldataUploadDetailService)
    {
        this.mgrExldataUploadDetailService = mgrExldataUploadDetailService;
    }
	
	
}
