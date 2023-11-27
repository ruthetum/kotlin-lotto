package lotto.adapter

import lotto.domain.LottoResult

data class LottoResultDto(
    val name: String,
    val price: Int,
    val count: Int,
) {
    companion object {
        fun from(lottoResult: LottoResult): MutableList<LottoResultDto> {
            val resultsDto = mutableListOf<LottoResultDto>()
            for (key in lottoResult.getLottoResultMapFilteredNotNone().keys) {
                val count = lottoResult.getLottoResultCount(key)
                resultsDto.add(LottoResultDto(key.description, key.price, count))
            }
            return resultsDto
        }
    }
}
