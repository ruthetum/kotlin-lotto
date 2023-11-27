package lotto.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class LottoRankMapTest {

    @DisplayName("미당첨을 제외한 로또 결과를 조회한다")
    @Test
    fun getLottoResultMapFilteredNotNoneTest() {
        // given
        val lottoRankMap = LottoResult.of(
            listOf(
                LottoRank.MATCH_6_NUMBERS,
                LottoRank.MATCH_4_NUMBERS,
                LottoRank.MATCH_5_NUMBERS,
                LottoRank.NONE,
            )
        )

        // when
        val filteredResultMap = lottoRankMap.getLottoResultMapFilteredNotNone()

        // then
        assertThat(filteredResultMap[LottoRank.MATCH_6_NUMBERS]).isEqualTo(1)
        assertThat(filteredResultMap[LottoRank.MATCH_5_NUMBERS]).isEqualTo(1)
        assertThat(filteredResultMap[LottoRank.MATCH_4_NUMBERS]).isEqualTo(1)
        assertThat(filteredResultMap[LottoRank.MATCH_3_NUMBERS]).isZero
    }

    @DisplayName("로또 결과 별 개수를 조회한다")
    @Test
    fun getLottoResultCountTest() {
        // given
        val lottoRankMap = LottoResult.of(
            listOf(
                LottoRank.MATCH_6_NUMBERS,
                LottoRank.NONE,
            )
        )
        val result = LottoRank.MATCH_6_NUMBERS

        // when
        val count = lottoRankMap.getLottoResultCount(result)

        // then
        assertThat(count).isEqualTo(1)
    }

    @DisplayName("전체 로또 개수를 조회한다")
    @Test
    fun getTotalCountTest() {
        // given
        val lottoRankMap = LottoResult.of(
            listOf(
                LottoRank.MATCH_6_NUMBERS,
                LottoRank.MATCH_4_NUMBERS,
                LottoRank.MATCH_5_NUMBERS,
                LottoRank.NONE,
            )
        )

        // when
        val count = lottoRankMap.getTotalCount()

        // then
        assertThat(count).isEqualTo(4)
    }

    @DisplayName("당첨 금액을 조회한다")
    @Test
    fun getWinningPriceTest() {
        // given
        val lottoRankMap = LottoResult.of(
            listOf(
                LottoRank.MATCH_6_NUMBERS,
                LottoRank.MATCH_4_NUMBERS,
                LottoRank.MATCH_5_NUMBERS,
                LottoRank.NONE,
            )
        )

        // when
        val actual = lottoRankMap.getWinningPrice()

        // then
        val expected = (
            LottoRank.MATCH_6_NUMBERS.price +
                LottoRank.MATCH_4_NUMBERS.price +
                LottoRank.MATCH_5_NUMBERS.price
            ).toDouble()
        assertThat(actual).isEqualTo(expected)
    }
}
