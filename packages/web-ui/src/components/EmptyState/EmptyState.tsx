import type { ReactNode } from 'react';
import type { TerraButtonType } from '../Button';
import type { TerraEmptyStateProps, TerraEmptyStateVariant } from './EmptyState.types';
import { TerraButton } from '../Button';
import './EmptyState.css';

import searchNotFoundImg from './illustrations/search-not-found.png';
import happyBirthdayImg from './illustrations/happy-birthday.png';
import connectionErrorImg from './illustrations/connection-error.png';
import underMaintenanceImg from './illustrations/under-maintenance.png';
import defaultSearchStateImg from './illustrations/default-search-state.png';
import pageNotFoundImg from './illustrations/page-not-found.png';
import serverBusyImg from './illustrations/server-busy.png';
import dataNotFoundImg from './illustrations/data-not-found.png';
import timeOutImg from './illustrations/time-out.png';
import successSubmitImg from './illustrations/success-submit.png';

type VariantConfig = {
  illustration: string;
  title: string;
  description: string;
  ctaLabel?: string;
  ctaVariant: TerraButtonType;
};

const VARIANTS: Record<TerraEmptyStateVariant, VariantConfig> = {
  searchNotFound: {
    illustration: searchNotFoundImg,
    title: 'Pencarian Tidak Ditemukan',
    description: 'Kami tidak menemukan kata kunci yang kamu cari, coba cari dengan kata kunci lainnya.',
    ctaLabel: 'Coba Lagi',
    ctaVariant: 'outlined-primary',
  },
  happyBirthday: {
    illustration: happyBirthdayImg,
    title: 'Selamat Ulang Tahun!',
    description: 'Kami mengucapkan selamat ulang tahun untuk:',
    ctaVariant: 'primary',
  },
  connectionError: {
    illustration: connectionErrorImg,
    title: 'Wah, Jaringan Internet Kamu Terganggu',
    description:
      'Coba cek jaringan internet kamu apakah sedang ada gangguan atau cek pulsa. Jika sudah, silahkan coba lagi.',
    ctaLabel: 'Coba Lagi',
    ctaVariant: 'primary',
  },
  underMaintenance: {
    illustration: underMaintenanceImg,
    title: 'Maaf, Sistem Sedang Dalam Perbaikan',
    description: 'Sistem sedang dalam perbaikan, kami akan memperbaikinya secepatnya.',
    ctaLabel: 'Kembali Ke Halaman Utama',
    ctaVariant: 'outlined-primary',
  },
  defaultSearchState: {
    illustration: defaultSearchStateImg,
    title: 'Belum Ada Pencarian',
    description: 'Kamu belum melakukan pencarian. Silahkan isi kolom diatas untuk melakukan pencarian.',
    ctaLabel: 'Coba Lagi',
    ctaVariant: 'primary',
  },
  pageNotFound: {
    illustration: pageNotFoundImg,
    title: 'Halaman Tidak Ditemukan',
    description: 'Halaman yang kamu cari tidak ditemukan, silahkan kembali ke halaman sebelumnya.',
    ctaLabel: 'Kembali',
    ctaVariant: 'primary',
  },
  serverBusy: {
    illustration: serverBusyImg,
    title: 'Internal Server Sedang Sibuk',
    description: 'Kami kesulitan untuk koneksikan menuju server, coba cek jaringan kamu atau coba lagi.',
    ctaLabel: 'Coba Lagi',
    ctaVariant: 'primary',
  },
  dataNotFound: {
    illustration: dataNotFoundImg,
    title: 'Data Tidak Ditemukan',
    description: 'Kami tidak menemukan data yang kamu cari, coba cari kembali.',
    ctaLabel: 'Kembali',
    ctaVariant: 'primary',
  },
  timeOut: {
    illustration: timeOutImg,
    title: 'Wah Waktu Kamu Habis',
    description: 'Waktu untuk login kamu telah habis, silahkan coba kembali untuk login.',
    ctaLabel: 'Coba Lagi',
    ctaVariant: 'primary',
  },
  successSubmit: {
    illustration: successSubmitImg,
    title: 'Data Sudah Dikirim',
    description: 'Data yang Anda ajukan sudah berhasil di submit, Silahkan meneruskan untuk proses selanjutnya.',
    ctaLabel: 'Selesai',
    ctaVariant: 'primary',
  },
};

function classNames(...values: Array<string | false | undefined>) {
  return values.filter(Boolean).join(' ');
}

export function TerraEmptyState({
  variant,
  title,
  description,
  ctaLabel,
  onCtaClick,
  illustration,
  className,
}: TerraEmptyStateProps) {
  const config = variant ? VARIANTS[variant] : undefined;

  const resolvedTitle = title ?? config?.title;
  const resolvedDescription = description ?? config?.description;
  const resolvedCtaLabel = ctaLabel === undefined ? config?.ctaLabel : ctaLabel ?? undefined;
  const ctaVariant = config?.ctaVariant ?? 'primary';

  let resolvedIllustration: ReactNode = illustration;
  if (resolvedIllustration === undefined && config) {
    resolvedIllustration = <img src={config.illustration} alt="" />;
  }

  return (
    <div className={classNames('terraEmptyState', className)}>
      {resolvedIllustration && (
        <div className="terraEmptyState__illustration">{resolvedIllustration}</div>
      )}
      <div className="terraEmptyState__message">
        {resolvedTitle && <p className="terraEmptyState__title">{resolvedTitle}</p>}
        {resolvedDescription && (
          <p className="terraEmptyState__description">{resolvedDescription}</p>
        )}
      </div>
      {resolvedCtaLabel && (
        <TerraButton
          variant={ctaVariant}
          className="terraEmptyState__cta"
          onClick={onCtaClick}
        >
          {resolvedCtaLabel}
        </TerraButton>
      )}
    </div>
  );
}
