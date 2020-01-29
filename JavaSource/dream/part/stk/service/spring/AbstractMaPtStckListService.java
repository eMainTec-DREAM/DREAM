package dream.part.stk.service.spring;

import java.util.List;

import common.bean.User;
import dream.part.stk.dto.MaPtStckCommonDTO;
import dream.part.stk.service.MaPtStckListService;

public abstract class AbstractMaPtStckListService implements MaPtStckListService {

	protected MaPtStckListService maPtStckListService;
	
	public AbstractMaPtStckListService(MaPtStckListService maPtStckListService)
	{
		this.maPtStckListService = maPtStckListService;
	}
	
	public abstract List findPtStckList(MaPtStckCommonDTO maPtStckCommonDTO, User user);
}
