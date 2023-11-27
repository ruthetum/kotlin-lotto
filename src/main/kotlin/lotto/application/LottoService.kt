package lotto.application

import lotto.domain.Lotto
import lotto.domain.LottoProperties
import lotto.domain.LottoRank
import lotto.domain.LottoResult
import lotto.domain.ProfitRate

class LottoService(
    private val properties: LottoProperties = LottoProperties(),
) : LottoUseCase {

    private fun payPriceAndGetCount(price: Int): Int {
        require(price > 0) { "지불하는 금액은 0보다 커야합니다" }
        return price / properties.lottoPrice
    }

    private fun generate(count: Int): List<Lotto> {
        return generateSequence { Lotto() }
            .take(count)
            .toList()
    }

    override fun buy(price: Int): List<Lotto> {
        val count = payPriceAndGetCount(price)
        return generate(count)
    }

    override fun matchWinningLotto(command: MatchWinningLottoCommand): LottoResult {
        val result = mutableListOf<LottoRank>()
        for (userLotto in command.userLottos) {
            result.add(userLotto.match(command.winningLotto))
        }
        return LottoResult.of(result)
    }

    override fun calculateProfitRate(lottoResult: LottoResult): ProfitRate {
        val totalLottoPrice = lottoResult.getTotalCount() * properties.lottoPrice
        val totalWinningPrice = lottoResult.getWinningPrice()
        return ProfitRate(totalWinningPrice / totalLottoPrice.toDouble())
    }
}
