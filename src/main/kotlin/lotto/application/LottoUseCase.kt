package lotto.application

import lotto.domain.Lotto
import lotto.domain.LottoResult
import lotto.domain.ProfitRate

interface LottoUseCase {
    fun buy(price: Int): List<Lotto>
    fun matchWinningLotto(command: MatchWinningLottoCommand): LottoResult
    fun calculateProfitRate(lottoResult: LottoResult): ProfitRate
}
