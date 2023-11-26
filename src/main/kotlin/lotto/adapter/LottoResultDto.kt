package lotto.adapter

import lotto.domain.LottoResultMap

data class LottoResultDto(
    val name: String,
    val price: Int,
    val count: Int,
) {
    companion object {
        fun from(lottoResultMap: LottoResultMap): MutableList<LottoResultDto> {
            val resultsDto = mutableListOf<LottoResultDto>()
            for (key in lottoResultMap.getLottoResultMapFilteredNotNone().keys) {
                val count = lottoResultMap.getLottoResultCount(key)
                resultsDto.add(LottoResultDto(key.description, key.price, count))
            }
            return resultsDto
        }
    }
}
