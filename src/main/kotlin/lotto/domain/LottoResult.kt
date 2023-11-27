package lotto.domain

import java.util.EnumMap

class LottoResult(
    private val lottoRankMap: EnumMap<LottoRank, Int>,
) {
    init {
        for (lottoResult in LottoRank.values()) {
            lottoRankMap[lottoResult] = lottoRankMap[lottoResult] ?: 0
        }
    }

    fun getLottoResultMapFilteredNotNone(): Map<LottoRank, Int> {
        return lottoRankMap.filter { it.key != LottoRank.NONE }
    }

    fun getLottoResultCount(lottoRank: LottoRank): Int {
        return lottoRankMap[lottoRank] ?: 0
    }

    fun getTotalCount(): Int {
        return lottoRankMap.values.sum()
    }

    fun getWinningPrice(): Double {
        return lottoRankMap.map { it.key.price * it.value }.sum().toDouble()
    }

    companion object {
        fun of(result: List<LottoRank>): LottoResult {
            val lottoRankMap = EnumMap<LottoRank, Int>(LottoRank::class.java)
            result.forEach { lottoRankMap[it] = lottoRankMap[it]?.plus(1) ?: 1 }
            return LottoResult(lottoRankMap)
        }
    }
}
